package com.cib.qdzg.api; // 导入包
// 添加注释

import com.cib.qdzg.entity.BankListWrapperEntity;
import com.cib.qdzg.entity.CardBinBean;
import com.cib.qdzg.entity.CardBinInfoBean;
import com.cib.qdzg.entity.CheckAcctInfoEntity;
import com.cib.qdzg.entity.CommonResponseEntity;
import com.cib.qdzg.entity.CreditCardBankEntity;
import com.cib.qdzg.http.HttpResponse;
import com.cib.qdzg.http.HttpResponseCardList;
import java.util.List;
import java.util.Map;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * 绑卡模块
 * Created by Shaolb on 2016/4/21
 */
public interface BindCardApi {

    /**
     * 验证姓名身份证
     *
     * @param customerName 姓名
     * @param idNo         身份证号
     */
    @POST("jsonRegisterVer2!validateCustom.do")
    @FormUrlEncoded
    Observable<HttpResponse<CommonResponseEntity>> validateCustom(@Field("customer.customerName") String customerName,
                                                                  @Field("customer.idNo") String idNo,
                                                                  @Field("customer.taxFlag") String taxFlag);

    /**
     * 获取联网行银行列表
     */
    @POST("queryBank!executeForApp.do")
    Observable<HttpResponse<BankListWrapperEntity>> getBankList();

    /**
     * 获取同名转账银行列表
     */
    @POST("queryBankForDebit.do")
    Observable<HttpResponseCardList<BankListWrapperEntity.BankListEntity>> getBankListOfSameName();

    /**
     * 根据银行卡前6位搜索对应银行信息
     *
     * @param cardBin 银行卡号
     * @return 银行信息
     */
    @POST("register!getUnCodeByAcctNoApp.do")
    @FormUrlEncoded
    Observable<HttpResponse<CardBinBean>> getUnCodeByAcctNoApp(@Field("cardBin") String cardBin);


    /**
     * 根据银行卡前6位或者前9位搜索对应银行信息
     *
     * @param cardBin 银行卡号
     * @return 银行信息
     */
    @POST("register!getBanksByCardBin.do")
    @FormUrlEncoded
    Observable<HttpResponse<CardBinInfoBean>> getBanksByCardBin(@Field("cardBin") String cardBin);
    /**
     * 单独获取五要素、六要素
     *
     * @param unConde 联网行银行Code
     */
    @POST("register!getVerifyByUnCode.do")
    @FormUrlEncoded
    Observable<HttpResponse<CardBinBean>> getVerifyByUnCode(@Field("ubCode") String unConde);

    /**
     * 银行卡验证链接
     *
     * @param acctNo   卡号
     * @param unCode   联网行编号
     * @param comeFrom 来源
     */
    @POST("jsonRegisterVer2!checkAcctInfo.do")
    @FormUrlEncoded
    Observable<HttpResponse<CheckAcctInfoEntity>> checkAcctInfo(@Field("bindInfo.acctNo") String acctNo,
                                                                @Field("bindInfo.ubCode") String unCode, @Field("bindInfo.comeFrom") String comeFrom);

    /**
     * 银行卡验证链接
     *
     * @param mobile  电话号码
     * @param acctPwd 银行卡密码
     * @param smsCode 验证码
     */
    @POST("jsonRegisterVer2!validateBindCard.do")
    @FormUrlEncoded
    Observable<HttpResponse<CommonResponseEntity>> validateBindCard(@Field("bindInfo.mobile") String mobile,
                                                                    @Field("bindInfo.acctPwd") String acctPwd, @Field("smsCode") String smsCode);

    /**
     * 验证密码
     *
     * @param pwd 密码
     */
    @POST("jsonRegisterVer2!validatePayPassword.do")
    @FormUrlEncoded
    Observable<HttpResponse<CommonResponseEntity>> validatePayPwd(@Field("bindInfo.ePwd") String pwd);

    /**
     * 前三步通过后，提交绑卡
     */
    @POST("jsonRegisterVer2!bindBankCard.do")
    Observable<HttpResponse<CommonResponseEntity>> bindBankCard();

    /**
     * 单独绑卡
     */
    @POST("financing/ver4/account/addAcct!bindAcctAppVer2.do")
    @FormUrlEncoded
    Observable<HttpResponse<CommonResponseEntity>> bindCardForAddcard(@FieldMap Map<String, Object> params);

    /**
     * 获取信用卡银行列表
     */
    @POST("queryBankCard.do?")
    Observable<HttpResponseCardList<List<CreditCardBankEntity>>> getCreditCardBankList();

}
