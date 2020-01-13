package com.qbank.qbank.service.inf;

import com.qbank.qbank.dto.MvcDataDto;
import com.qbank.qbank.entity.User;

import java.util.List;

/**
 * @author 王宇杰
 * @date 2020/1/9 14:30
 */
public interface IUserService {
    /**
     * 用户登录
     *
     * @param workNumber 学工号
     * @param password   密码
     * @return MvcDataDto
     * @throws Exception Exception
     */
    MvcDataDto login(String workNumber, String password) throws Exception;

    /**
     * 用户注册
     *
     * @param user 用户数据
     * @return MvcDataDto
     * @throws Exception Exception
     */
    MvcDataDto register(User user) throws Exception;

    /**
     * 用户批量注册
     *
     * @param list 用户数据
     * @return MvcDataDto
     * @throws Exception Exception
     */
    MvcDataDto batchRegister(List<User> list) throws Exception;

    /**
     * 上传头像
     *
     * @param userId      用户id
     * @param imgByBase64 图片base64编码
     * @return MvcDataDto
     * @throws Exception Exception
     */
    MvcDataDto uploadCase(String userId, String imgByBase64) throws Exception;

}
