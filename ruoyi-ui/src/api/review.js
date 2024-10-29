import request from '@/utils/request'

// 查询图书评论管理列表
export function listReview(query) {
  return request({
    url: '/system/review/list',
    method: 'get',
    params: query
  })
}

// 查询图书评论管理详细
export function getReview(id) {
  return request({
    url: '/system/review/' + id,
    method: 'get'
  })
}

// 新增图书评论管理
export function addReview(data) {
  return request({
    url: '/system/review',
    method: 'post',
    data: data
  })
}

// 修改图书评论管理
export function updateReview(data) {
  return request({
    url: '/system/review',
    method: 'put',
    data: data
  })
}

// 删除图书评论管理
export function delReview(id) {
  return request({
    url: '/system/review/' + id,
    method: 'delete'
  })
}
