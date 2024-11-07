package org.zerock.mmh.controller;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.zerock.mmh.dto.ProductDTO;
import org.zerock.mmh.dto.ServiceDTO;
import org.zerock.mmh.entity.ManufacturerInfo;
import org.zerock.mmh.entity.Product;
import org.zerock.mmh.entity.mService;
import org.zerock.mmh.service.ManufacturerInfoService;
import org.zerock.mmh.service.ManufacturerServiceService;
import org.zerock.mmh.service.ProductService;
import org.zerock.mmh.service.ServiceService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SearchRestController {

    //데이터를 가져올 db
    @Autowired
    private ServiceService serviceService;

    @Autowired
    private ProductService productService;

//    @RequestMapping("/autoServiceInfo")
//    public List<String> autoServiceInfo() throws Exception {
//        //db에서 상품(product)에 해당하는 서비스 값을 가지고 온다.
//        List<Service> services = serviceService.getServiceList();
//        List<String> result = new ArrayList<String>();
//
//        //이름만 담아서 반환
//        for (Service service : services) {
//            result.add(service.getService_name());
//        }
//        return result;
//    }

    @RequestMapping("/autoServiceInfo")
    public List<mService> autoServiceInfo() throws Exception {
        return serviceService.getServiceList();
    }

    @RequestMapping("/currentProductInfo")
    public List<Product> currentProductInfo() throws Exception {
        return productService.getProductList();
    }

    @RequestMapping("/autoProductInfo")
    public List<Product> autoProductInfo() throws Exception {
        return productService.getProductList();
    }





//    @RequestMapping("/getServiceNo/{manuInfoNo}")
//    public List<ServiceDTO> getServiceNo(@PathVariable("manuInfoNo") String manuInfoNo) throws Exception {
//        return serviceService.getListWithManuInfoNo(manuInfoNo);
//    }

}
