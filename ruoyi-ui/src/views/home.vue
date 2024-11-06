<template>
  <div class="book-page">
    <!-- 导航按钮 -->
    <el-button class="home-button" type="primary" @click="goToHome">后台管理</el-button>
    <el-button class="cart-button" type="primary" @click="showCart">查看购物车 ({{ cartItems.length }})</el-button>
    <!-- 分类按钮 -->
    <el-row :gutter="20" class="category-buttons">
      <el-col v-for="(books, category) in categorizedBooks" :key="category" :span="4">
        <el-button
          :class="['category-button', { active: selectedCategory === category }]"
          type="default"
          @click="selectCategory(category)"
        >
          {{ category }}
        </el-button>
      </el-col>
    </el-row>

    <!-- 当前分类的书籍展示 -->
    <el-row v-for="(books, category) in categorizedBooks" v-if="selectedCategory === category" :key="category" gutter="20">
      <el-col :span="24">
        <h2 class="category-title">{{ category }}</h2>
      </el-col>
      <el-col v-for="book in books" :key="book.id" :span="8">
        <el-card class="book-card animated-card" :body-style="{ padding: '20px' }">
          <div class="image-container">
            <image-preview :src="book.image" :width="200" :height="200" />
          </div>
          <div class="book-info">
            <h3 class="book-title">{{ book.name }}</h3>
            <p><strong>作者：</strong>{{ book.author }}</p>
            <p>
              <strong>价格：</strong>
              <span :class="{ 'highlight-price': book.bargainPrice === 1 || book.recommended === 1 }">
                ￥{{ book.price }}
              </span>
              <span v-if="book.bargainPrice === 1" class="tag bargain-tag">特价</span>
              <span v-if="book.recommended === 1" class="tag recommended-tag">推荐</span>
            </p>
            <p><strong>描述：</strong>{{ book.content }}</p>
            <el-button class="animated-button" type="primary" @click="handleAddToCart(book)">加入购物车</el-button>
            <el-divider />
            <div class="book-review">
              <h4>用户书评</h4>
              <div v-if="book.book_reviews && book.book_reviews.length">
                <div v-for="reviewList in book.book_reviews" :key="reviewList.id" class="review-item">
                  <p><strong>{{ reviewList.username }}:</strong> {{ reviewList.review }}</p>
                </div>
              </div>
              <el-input v-model="book.newReview" placeholder="输入您的书评"></el-input>
              <el-button
                type="primary"
                class="animated-button"
                @click="submitReview(book)"
              >提交书评</el-button>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <!-- 购物车对话框 -->
    <el-dialog :visible.sync="cartVisible" title="购物车" width="50%">
      <el-table :data="cartItems" style="width: 100%">
        <el-table-column prop="name" label="书名" />
        <el-table-column prop="price" label="价格" />
        <el-table-column prop="quantity" label="数量" />
        <el-table-column label="操作">
          <template #default="{ row }">
            <el-button @click="removeItem(row.id)" type="danger" size="small">移除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="cart-summary">
        <span>总价: ￥{{ cartTotal }}</span>
      </div>

      <!-- 新增送货时间选择 -->
      <el-date-picker
        v-model="deliveryDate"
        type="datetime"
        placeholder="选择希望送货时间"
      ></el-date-picker>

      <span slot="footer" class="dialog-footer">
        <el-button @click="cartVisible = false">关闭</el-button>
        <el-button type="primary" @click="checkout">结账</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { listBook } from "@/api/system/book";
import { listReview, addReview } from "@/api/review";
import { addOrder } from "@/api/system/order";

export default {
  data() {
    return {
      bookList: [],
      categorizedBooks: {},
      selectedCategory: '', // 当前选中的分类
      cartItems: [], // 购物车中的书籍列表
      deliveryDate: '', // 用户希望的送货时间
      cartVisible: false // 控制购物车对话框的显示
    };
  },
  computed: {
    // 计算购物车总价
    cartTotal() {
      return this.cartItems.reduce((total, item) => total + item.price * item.quantity, 0);
    }
  },
  created() {
    this.getList();
  },
  methods: {
    goToHome() {
      this.$router.push('/index'); // 使用 Vue Router 的 push 方法跳转
    },
    // 获取图书列表
    getList() {
      listBook({ pageNum: 1, pageSize: 10 })
        .then(response => {
          this.bookList = response.rows.map(book => ({
            ...book,
            book_reviews: [], // 初始化 reviews 为空数组
            newReview: '' // 初始化 newReview 为空字符串
          }));
          this.categorizeBooks();
          this.getReviewList(); // 在获取书籍数据后调用获取评论数据
        })
        .catch(error => {
          console.error("获取图书列表失败：", error);
        });
    },
    // 获取评论列表并将其关联到对应图书
    getReviewList() {
      listReview({ pageNum: 1, pageSize: 10 })
        .then(response => {
          const reviews = response.rows;
          reviews.forEach(review => {
            const book = this.bookList.find(b => b.id === review.bookId);
            if (book) {
              book.book_reviews.push({
                id: review.id,
                username: review.username,
                review: review.review,
              });
            }
          });
        })
        .catch(error => {
          console.error("获取图书评论列表失败：", error);
        });
    },
    // 将书籍按类别分类
    categorizeBooks() {
      this.categorizedBooks = this.bookList.reduce((acc, book) => {
        const category = book.category || "其他";
        if (!acc[category]) {
          acc[category] = [];
        }
        acc[category].push(book);
        return acc;
      }, {});
      // 默认显示第一个分类
      this.selectedCategory = Object.keys(this.categorizedBooks)[0] || '';
    },
    // 切换分类
    selectCategory(category) {
      this.selectedCategory = category;
    },
    // 提交书评
    submitReview(book) {
      if (!book.newReview.trim()) return;

      const review = {
        review: book.newReview,
        username: '我', // 模拟当前用户
        bookId: book.id
      };

      addReview(review)
        .then(() => {
          this.$message.success("评论成功！");
          book.book_reviews.push({ ...review }); // 在成功回调中更新前端评论显示
          book.newReview = ''; // 清空输入框
        })
        .catch(error => {
          console.error("提交评论失败：", error);
        });
    },

    // 将书籍加入购物车
    handleAddToCart(book) {
      const existingItem = this.cartItems.find(item => item.id === book.id);
      if (existingItem) {
        existingItem.quantity += 1;
      } else {
        this.cartItems.push({
          id: book.id,
          name: book.name,
          price: book.price,
          quantity: 1
        });
      }
      this.$message.success(`已将《${book.name}》加入购物车！`);
    },

    // 显示购物车
    showCart() {
      this.cartVisible = true;
    },

    // 移除购物车中的书籍
    removeItem(bookId) {
      const index = this.cartItems.findIndex(item => item.id === bookId);
      if (index !== -1) {
        this.cartItems.splice(index, 1);
        this.$message.success("书籍已从购物车中移除！");
      }
    },

    // 结账处理
    checkout() {
      const orderData = {
        items: JSON
          .stringify(this.cartItems),
        deliveryDate: this.deliveryDate,
        totalPrice: this.cartTotal,
      };

      addOrder(orderData)
        .then(() => {
          this.$message.success("订单提交成功");
          this.cartItems = [];
          this.deliveryDate = '';
          this.cartVisible = false;
        })
        .catch(error => {
          console.error("订单提交失败：", error);
        });
    }
  }
};
</script>

<style scoped>
.home-button, .cart-button {
  margin-bottom: 20px;
  position: fixed;
  top: 10px;
  z-index: 1000;
}

.home-button {
  left: 20px;
}

.cart-button {
  right: 20px;
}

.book-page {
  padding: 20px;
  background: linear-gradient(135deg, #f3f4f6, #e9eff5);
  font-family: Arial, sans-serif;
}

.page-title {
  text-align: center;
  margin-bottom: 30px;
  font-size: 2.5em;
  color: #444;
  text-shadow: 1px 1px 2px #ccc;
}

.category-buttons {
  display: flex;
  flex-wrap: nowrap;  /* 防止按钮换行 */
  justify-content: flex-start; /* 从左到右排列 */
  margin-bottom: 20px;
}

.category-button {
  flex: 1 1 auto; /* 使按钮宽度自适应并且填满空白区域 */
  margin: 80px 0 0 0; /* 清除默认的按钮间隙 */
  border-radius: 5px;
  padding: 10px 30px; /* 增加按钮的内边距，增加点击区域 */
  font-size: 1.1em;
  color: #333;
  border: 1px solid #ddd;
  background-color: #f5f5f5;
  transition: background-color 0.3s, color 0.3s;
}

.category-button.active {
  background-color: #48c86c; /* 激活状态的背景色 */
  color: white;
  border-color: #48c86c; /* 激活状态的边框颜色 */
}

.category-title {
  margin: 20px 0;
  font-size: 1.8em;
  color: #666;
  border-bottom: 2px solid #ddd;
  padding-bottom: 10px;
}

.image-container {
  text-align: center;
  padding: 10px;
}

.book-info {
  margin-top: 15px;
  text-align: left;
}

.book-title {
  font-size: 1.6em;
  margin-bottom: 10px;
  color: #333;
}

.highlight-price {
  color: #48c86c;
  font-weight: bold;
  font-size: 1.2em;
}

.tag {
  margin-left: 10px;
  font-weight: bold;
  color: white;
  padding: 2px 5px;
  border-radius: 3px;
}

.bargain-tag {
  background-color: red;
}

.recommended-tag {
  background-color: green;
}

.animated-button {
  margin-top: 10px;
  background: linear-gradient(to right, #48c86c, #34b7a7);
  color: #fff;
  font-weight: bold;
  border-radius: 5px;
}

.cart-summary {
  margin-top: 20px;
  font-weight: bold;
  text-align: right;
}

/* 对话框样式 */
.el-dialog {
  max-width: 80%;
}

.el-dialog .el-table {
  margin-top: 20px;
}

.el-dialog .dialog-footer {
  display: flex;
  justify-content: space-between;
  padding: 10px 0;
}

/* 响应式样式 */
@media (max-width: 768px) {
  .category-buttons {
    flex-wrap: wrap; /* 在小屏幕上允许换行 */
  }

  .category-button {
    flex: 0 1 45%; /* 小屏幕上按钮占据一半宽度 */
    margin: 5px;
  }

  .home-button, .cart-button {
    position: relative;
    top: auto;
    left: auto;
    right: auto;
  }

  .book-card {
    margin-bottom: 20px;
  }
}
</style>
