import axios from 'axios'
import {BLOG_MODULE} from "./_prefix";

/**
 * 创建一个博客 POST /blogs
 * @param payload
 * @returns {Promise<AxiosResponse<any>>}
 */
export const createBlog = payload => {
  const {title, content} = payload;
  let newContent=window.encodeURIComponent(content);
  return axios
    .post(`${BLOG_MODULE}/blogs/?title=${title}&content=${newContent}`)
    .then(res => {
      console.log(window.encodeURI(content));
      return res.data;
    });
};

/**
 * 修改博客 PUT /blogs
 * @param payload
 * @returns {Promise<AxiosResponse<any>>}
 */
export const updateBlog = (payload) => {
  const {blog_id, title, content} = payload;
  console.log(payload);
  return axios
    .put(`${BLOG_MODULE}/blogs/?blog_id=${blog_id}&title=${title}&content=${content}`)
    .then(res => {
      return res.data;
    });
};

export const deleteBlog = payload => {
  const {blog_id} = payload;
  console.log(blog_id);
  return axios
    .delete(`${BLOG_MODULE}/blogs/?blog_id=${blog_id}`)
    .then(res => {
      return res.data;
    });
};

export const findByTitle = payload => {
  const {keyword} = payload;
  console.log(payload);
  return axios
    .get(`${BLOG_MODULE}/blogs/search/?keyword=${keyword}`)
    .then(res => {
      return res.data;
    });
};

export const like = payload => {
  const {blog_id} = payload;
  console.log(payload);
  return axios
    .post(`${BLOG_MODULE}/blogs/like/?blog_id=${blog_id}`)
    .then(res => {
      return res.data;
    })
}



