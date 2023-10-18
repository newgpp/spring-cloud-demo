package com.felix.controller;

import com.felix.dao.transaction.AccountHistoryMapper;
import com.felix.entity.transaction.AccountHistory;
import com.felix.utils.PageVO;
import com.felix.utils.Result;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @author felix
 * @desc 参数校验
 * https://www.baeldung.com/spring-validate-requestparam-pathvariable
 */
@RestController
@RequestMapping("/page/account/history")
@Validated
public class PageController {

    private static final Logger log = LoggerFactory.getLogger(PageController.class);

    @Resource
    private AccountHistoryMapper accountHistoryMapper;

    @GetMapping("/without/number")
    public Result<List<AccountHistory>> getNoNumPage(@RequestParam(value = "accountId", required = false) @NotNull Long accountId
            , @RequestParam(value = "startTs", required = false) @Min(1) Long startTs
            , @RequestParam(value = "endTs", required = false) @Min(1) Long endTs
            , @RequestParam(value = "from", required = false) @Min(0) Long from
            , @RequestParam(value = "size", required = false) @NotNull @Min(1) Integer size
            , @RequestParam(value = "direct", required = false) @NotNull(message = "direct不允许为null") @Pattern(regexp = "next|prev") String direct) {
        List<AccountHistory> list = accountHistoryMapper.selectPage(accountId, startTs, endTs, from, size, direct);
        log.info("getNoNumPage, list: {}", list);
        return Result.success(list);
    }

    @GetMapping("/with/number")
    public Result<PageVO<AccountHistory>> getNoNumPage(@RequestParam(value = "accountId", required = false) @NotNull Long accountId
            , @RequestParam(value = "startTs", required = false) @Min(1) Long startTs
            , @RequestParam(value = "endTs", required = false) @Min(1) Long endTs
            , @RequestParam(value = "pageNum", required = false) @NotNull @Min(1) Integer pageNum
            , @RequestParam(value = "pageSize", required = false) @NotNull @Min(1) Integer pageSize) {

        Page<AccountHistory> page = PageHelper.startPage(pageNum, pageSize).doSelectPage(() -> accountHistoryMapper.selectList(accountId, startTs, endTs));

        PageVO<AccountHistory> pageVO = new PageVO<>();
        pageVO.setList(page.getResult());
        pageVO.setPageNum(pageNum);
        pageVO.setPageSize(pageSize);
        pageVO.setTotal(page.getTotal());

        return Result.success(pageVO);
    }


}
