<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="价格" prop="price">
        <el-input
          v-model="queryParams.price"
          placeholder="请输入价格"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="作者" prop="author">
        <el-input
          v-model="queryParams.author"
          placeholder="请输入作者"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="分类" prop="category">
        <el-input
          v-model="queryParams.category"
          placeholder="请输入分类"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="数量" prop="count">
        <el-input
          v-model="queryParams.count"
          placeholder="请输入数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否特价" prop="bargainPrice">
        <el-select v-model="queryParams.bargainPrice" placeholder="请选择是否特价" clearable>
          <el-option
            v-for="dict in dict.type.sys_normal_disable"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="是否推荐" prop="recommended">
        <el-select v-model="queryParams.recommended" placeholder="请选择是否推荐" clearable>
          <el-option
            v-for="dict in dict.type.sys_normal_disable"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="发布人" prop="createUser">
        <el-input
          v-model="queryParams.createUser"
          placeholder="请输入发布人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:book:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:book:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:book:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:book:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="bookList" @selection-change="handleSelectionChange" highlight-current-row
              :row-class-name="tableRowClassName">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="名称" align="center" prop="name" />
      <el-table-column label="价格" align="center" prop="price" />
      <el-table-column label="作者" align="center" prop="author" />
      <el-table-column label="分类" align="center" prop="category" />
      <el-table-column label="内容" align="center" prop="content" />
      <el-table-column label="数量" align="center" prop="count" />
      <el-table-column label="封面url" align="center" prop="image" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.image" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="是否可售" align="center" key="status" >
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.status"
            :active-value=0
            :inactive-value=1
            @change="handleStatusChange(scope.row)"
          ></el-switch>
        </template>
      </el-table-column>
      <el-table-column label="是否特价" align="center" prop="bargainPrice">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.bargainPrice"
            :active-value=1
            :inactive-value=0
            @change="handleBargainPriceChange(scope.row)"
          ></el-switch>
        </template>
      </el-table-column>
      <el-table-column label="是否推荐" align="center" prop="recommended">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.recommended"
            :active-value=1
            :inactive-value=0
            @change="handleRecommendedChange(scope.row)"
          ></el-switch>
        </template>
      </el-table-column>
      <el-table-column label="发布人" align="center" prop="createUser" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:book:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:book:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改图书管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入名称" />
        </el-form-item>
        <el-form-item label="价格" prop="price">
          <el-input v-model="form.price" placeholder="请输入价格" />
        </el-form-item>
        <el-form-item label="作者" prop="author">
          <el-input v-model="form.author" placeholder="请输入作者" />
        </el-form-item>
        <el-form-item label="分类" prop="category">
          <el-input v-model="form.category" placeholder="请输入分类" />
        </el-form-item>
        <el-form-item label="内容">
          <editor v-model="form.content" :min-height="192"/>
        </el-form-item>
        <el-form-item label="数量" prop="count">
          <el-input v-model="form.count" placeholder="请输入数量" />
        </el-form-item>
        <el-form-item label="封面url" prop="image">
          <image-upload v-model="form.image"/>
        </el-form-item>
        <el-form-item label="是否可售" prop="status">
          <el-checkbox-group v-model="form.status">
            <el-checkbox
              v-for="dict in dict.type.sys_show_hide"
              :key="dict.value"
              :label="dict.value">
              {{dict.label}}
            </el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="是否特价" prop="bargainPrice">
          <el-radio-group v-model="form.bargainPrice">
            <el-radio
              v-for="dict in dict.type.sys_normal_disable"
              :key="dict.value"
              :label="parseInt(dict.value)"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="是否推荐" prop="recommended">
          <el-radio-group v-model="form.recommended">
            <el-radio
              v-for="dict in dict.type.sys_normal_disable"
              :key="dict.value"
              :label="parseInt(dict.value)"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="发布人" prop="createUser">
          <el-input v-model="form.createUser" placeholder="请输入发布人" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listBook, getBook, delBook, addBook, updateBook } from "@/api/system/book";

export default {
  name: "Book",
  dicts: ['sys_normal_disable', 'sys_show_hide'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 图书管理表格数据
      bookList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        price: null,
        author: null,
        category: null,
        content: null,
        count: null,
        image: null,
        status: null,
        bargainPrice: null,
        recommended: null,
        createUser: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
    methods: {
      tableRowClassName({ row }) {
        const classes = [];
        if (row.bargainPrice === 1) classes.push('bargain');
        if (row.recommended === 1) classes.push('recommended');
        if (row.bargainPrice === 1 && row.recommended === 1) classes.push('bargain-recommended');
        return classes.join(' '); // 返回以空格分隔的类名字符串
      },
      /** 查询图书管理列表 */
      getList() {
        this.loading = true;
        listBook(this.queryParams).then(response => {
          this.bookList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          id: null,
          name: null,
          price: null,
          author: null,
          category: null,
          content: null,
          count: null,
          image: null,
          status: [],
          bargainPrice: null,
          recommended: null,
          createTime: null,
          createUser: null
        };
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.resetForm("queryForm");
        this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.id)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加图书管理";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const id = row.id || this.ids;
        getBook(id).then(response => {
          this.form = response.data;

          // 将 status 转为字符串，然后进行 split
          this.form.status = String(this.form.status).split(",");

          this.open = true;
          this.title = "修改图书管理";
        }).catch(error => {
          console.error("Error fetching book data:", error);
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            this.form.status = this.form.status.join(",");
            if (this.form.id != null) {
              updateBook(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addBook(this.form).then(response => {
                this.$modal.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const ids = row.id || this.ids;
        this.$modal.confirm('是否确认删除图书管理编号为"' + ids + '"的数据项？').then(function() {
          return delBook(ids);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {
        });
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('system/book/export', {
          ...this.queryParams
        }, `book_${new Date().getTime()}.xlsx`)
      },
      handleStatusChange(row) {
        // 构造要发送的数据
        const updatedData = {
          id: row.id,
          status: row.status
        };

        // 调用 updateBook 函数更新数据
        updateBook(updatedData).then(response => {
          // 根据需要处理响应
          this.$message.success("状态更新成功");
        }).catch(error => {
          // 处理错误
          this.$message.error("状态更新失败");
          // 恢复状态以防止数据不一致
          row.status = row.status === 0 ? 1 : 0; // 这里恢复到原状态
        });
      },
      handleBargainPriceChange(row) {
        // 构造要发送的数据
        const updatedData = {
          id: row.id,
          bargainPrice: row.bargainPrice
        };

        // 调用 updateBook 函数更新数据
        updateBook(updatedData).then(response => {
          // 根据需要处理响应
          this.$message.success("状态更新成功");
        }).catch(error => {
          // 处理错误
          this.$message.error("状态更新失败");
          // 恢复状态以防止数据不一致
          row.bargainPrice = row.bargainPrice === 0 ? 1 : 0; // 这里恢复到原状态
        });
      },
      handleRecommendedChange(row) {
        // 构造要发送的数据
        const updatedData = {
          id: row.id,
          recommended: row.recommended
        };

        // 调用 updateBook 函数更新数据
        updateBook(updatedData).then(response => {
          // 根据需要处理响应
          this.$message.success("状态更新成功");
        }).catch(error => {
          // 处理错误
          this.$message.error("状态更新失败");
          // 恢复状态以防止数据不一致
          row.recommended = row.recommended === 0 ? 1 : 0; // 这里恢复到原状态
        });
      },
    }
  }
</script>
<style scoped>
.el-table ::v-deep .bargain {
  background-color: #C6EDDC;
}
.el-table ::v-deep .recommended {
  background-color: #F2E9E0;
}
.el-table ::v-deep .bargain-recommended {
  background-color: #409EFF;
}
</style>
