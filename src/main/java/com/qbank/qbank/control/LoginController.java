package com.qbank.qbank.control;

import com.qbank.qbank.dao.inf.IUserDao;
import com.qbank.qbank.dto.MvcDataDto;
import com.qbank.qbank.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedList;
import java.util.List;

import static com.qbank.qbank.service.impl.UserServiceImpl.getUserService;


/**
 * @author 王宇杰
 * @date 2020/1/11 19:10
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("/index")
    public String index() {
        return "login";
    }

    @RequestMapping("/login")
    @ResponseBody
    public Object login(@RequestParam("user_work_number") String userWorkNumber, @RequestParam("user_password") String userPassword) throws Exception {
        MvcDataDto data = getUserService().login(userWorkNumber, userPassword, IUserDao.CLASS_USERWORKNUMBER);
        if (MvcDataDto.SUCCESS==data.getCode()) {
            data.setUrl("/QBank/main");
        }
        return data;
    }

    @RequestMapping("/register_user")
    @ResponseBody
    public Object registerUser(@RequestParam("user_name") String userName, @RequestParam("user_work_number") String userWorkNumber,
                                   @RequestParam("user_password") String userPassword, @RequestParam("user_college") String userCollege,
                                   @RequestParam("user_phone_number") String userPhoneNumber) throws Exception {
        User user = new User();
        user.setUserName(userName);
        user.setUserWorkNumber(userWorkNumber);
        user.setUserPassword(userPassword);
        user.setUserCollege(userCollege);
        user.setUserPhoneNumber(userPhoneNumber);
        return getUserService().register(user);
    }

    @RequestMapping("/batch_register_user")
    @ResponseBody
    public Object batchRegisterUser(@RequestParam("user_name") String[] userNames, @RequestParam("user_work_number") String[] userWorkNumbers,
                                        @RequestParam("user_password") String[] userPasswords, @RequestParam("user_college") String[] userColleges,
                                        @RequestParam("user_phone_number") String[] userPhoneNumbers) throws Exception {
        List<User> list = new LinkedList<>();
        for (int i = 0; i < userNames.length; i++) {
            User user = new User();
            user.setUserName(userNames[i]);
            user.setUserWorkNumber(userWorkNumbers[i]);
            user.setUserPassword(userPasswords[i]);
            user.setUserCollege(userColleges[i]);
            user.setUserPhoneNumber(userPhoneNumbers[i]);
            list.add(user);
        }
        return getUserService().batchRegister(list);
    }

}
