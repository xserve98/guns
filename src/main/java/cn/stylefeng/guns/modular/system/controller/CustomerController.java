/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.stylefeng.guns.modular.system.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.guns.core.common.annotion.BussinessLog;
import cn.stylefeng.guns.core.common.constant.dictmap.CustomerMap;
import cn.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.modular.system.entity.Customer;
import cn.stylefeng.guns.modular.system.service.CustomerService;
import cn.stylefeng.guns.modular.system.warpper.CustomerWrapper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;

/**
 * 客户控制器
 *
 * @author fengshuonan
 * @Date 2017-05-09 23:02:21
 */
@Controller
@RequestMapping("/customer")
public class CustomerController extends BaseController {

    private String PREFIX = "/modular/system/customer/";

    @Autowired
    private CustomerService customerService;

    /**
     * 跳转到客户列表首页
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:06 PM
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "customer.html";
    }

    /**
     * 跳转到添加客户
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:06 PM
     */
    @RequestMapping("/customer_add")
    public String CustomerAdd() {
        return PREFIX + "customer_add.html";
    }

    /**
     * 跳转到修改客户
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:06 PM
     */
    @RequestMapping("/customer_update/{customerId}")
    public String CustomerUpdate(@PathVariable Long customerId, Model model) {
        Customer customer = this.customerService.getById(customerId);
        model.addAllAttributes(BeanUtil.beanToMap(customer));
        LogObjectHolder.me().set(customer);
        return PREFIX + "Customer_edit.html";
    }


    /**
     * 获取客户列表
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:06 PM
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        Page<Map<String, Object>> list = this.customerService.list(condition);
        Page<Map<String, Object>> wrap = new CustomerWrapper(list).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
    }

    /**
     * 新增客户
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:06 PM
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    @BussinessLog(value = "新增客户", key = "title", dict = CustomerMap.class)
    public Object add(Customer customer) {
        if (ToolUtil.isOneEmpty(customer, customer.getName(), customer.getContact())) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        customer.setCreateUser(ShiroKit.getUserNotNull().getId());
        customer.setCreateTime(new Date());
        this.customerService.save(customer);
        return SUCCESS_TIP;
    }

    /**
     * 删除客户
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:06 PM
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    @BussinessLog(value = "删除客户", key = "CustomerId", dict = CustomerMap.class)
    public Object delete(@RequestParam Long CustomerId) {

        //缓存客户名称
        LogObjectHolder.me().set(ConstantFactory.me().getCustomerName(CustomerId));

        this.customerService.removeById(CustomerId);

        return SUCCESS_TIP;
    }

    /**
     * 修改客户
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:06 PM
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    @BussinessLog(value = "修改客户", key = "title", dict = CustomerMap.class)
    public Object update(Customer customer) {
        if (ToolUtil.isOneEmpty(customer, customer.getCustomerId(), customer.getName(), customer.getContact())) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        Customer old = this.customerService.getById(customer.getCustomerId());
        BeanUtils.copyProperties(customer, old);
        old.setUpdateTime(new Date());
        old.setUpdateUser(ShiroKit.getUserNotNull().getId());
        this.customerService.updateById(old);
        return SUCCESS_TIP;
    }

}
