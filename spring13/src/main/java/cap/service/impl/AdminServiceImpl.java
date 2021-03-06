package cap.service.impl;


import cap.bean.Admin;
import cap.dao.AdminDAO;
import cap.service.AdminServicce;
import cap.util.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class AdminServiceImpl implements AdminServicce {

    @Resource
    private AdminDAO adminDAO;

    @Override
    public PageBean findByPage(int pageNo, int pageSize) {
        PageBean pageBean = new PageBean();
        pageBean.setPageNo(pageNo);
        pageBean.setPageSize(pageSize);
        pageBean.setList(adminDAO.findByPage(pageNo, pageSize));
        pageBean.setTotalRecords(adminDAO.getTotal());
        return pageBean;
    }

    @Override
    public int addAdmin(Admin admin) {
        return adminDAO.addAdmin(admin);
    }

}