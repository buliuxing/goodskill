package org.goodskill.web;

import org.goodskill.entity.Goods;
import org.goodskill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author ：yangzengrui
 * @date ：Created in 17/3/2019 3:28 PM
 * @description：
 */

@Controller
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
    private SeckillService seckillService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model){

        List<Goods> list = seckillService.getGoodsList();
        model.addAttribute("list", list);
        return null;
    }

    @RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model){

        if(seckillId == null){
            return "redirect:/seckill/list";
        }

        Goods good = seckillService.getGoodById(seckillId);
        if(good == null){
            return "forward:/seckill/list";
        }
        model.addAttribute("Goods", good);
        return "detail";

    }

    

}
