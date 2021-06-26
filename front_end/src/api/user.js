import axios from "axios";
import {USER_MODULE} from "./_prefix";

/**
 * 用户注册 POST /users
 * @param payload
 * @returns {Promise<AxiosResponse<any>>}
 */
export const signUp = payload => {
  console.log(payload);
  const {email, password, name} = payload;

  return axios
    .post(`${USER_MODULE}/users`, payload)
    .then(res => {
      return res.data;
    });
};

export const login = payload => {
  console.log(payload);
  const {email, password} = payload;
  return axios
    .get(`${USER_MODULE}/sign-in/?email=${email}&password=${password}`)
    .then(res => {
      return res.data;
    })
};
