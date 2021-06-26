import axios from 'axios'
import {BLOG_MODULE} from "./_prefix";

/**
 * 创建一条评论 POST /comments
 * @param payload
 * @returns {Promise<AxiosResponse<any>>}
 */
export const createComment = payload => {
  const {content,id} = payload;
  return axios
    .post(`${BLOG_MODULE}/comments/?content=${content}&blog_id=${id}`)
    .then(res => {
      return res.data;
    });
};

/**
 * 创建一条回复 POST /replies
 * @param payload
 * @returns {Promise<AxiosResponse<any>>}
 */
export const createReply = payload => {
  const {content, id} = payload;
  console.log(content);
  return axios
    .post(`${BLOG_MODULE}/replies/?content=${content}&comment_id=${id}`)
    .then(res => {
      return res.data;
    });
};

export const getComment = payload => {
  const {id} = payload;
  console.log(id);
  return axios
    .get(`${BLOG_MODULE}/comments/find/?com_id=${id}`)
    .then(res => {
      return res.data;
    });
};

export const getReply = payload => {
  const {id} = payload;
  console.log(id);
  return axios
    .get(`${BLOG_MODULE}/replies/find/?com_id=${id}`)
    .then(res => {
      return res.data;
    });
};
