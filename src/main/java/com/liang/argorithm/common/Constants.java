package com.liang.argorithm.common;

public class Constants {

  /* 默认分页个数 */
  public final static int DEFAULT_PAGE_SIZE = 20;

  //管理员不能被取消的两个菜单
  public static final int ROLE_MANAGE_FUNC_ID = 6;//角色管理的菜单主键
  public static final int USER_MANAGE_FUNC_ID = 8;//用户管理的菜单主键
  public static final String ENT_ADMIN_ROLE_CODE = "SYS_020";


  public final static String USER_CODE = "usercode";

  public final static String USER_NAME = "username";
  /**
   * 生成token前缀
   */
  public final static String TOKEN_IDENTIFY = "TOKEN:";
  /**
   * 建立userid-token关系时，为userid加前缀
   */
  public final static String USERID_IDENTIFY = "USERID:";

  /**
   * 登录设置的过期时长（分钟）
   */
  public final static long MOBILE__TIMEOUTS = 5;
  /**
   * zuul转发-用户id
   */
  public final static String ZUUL_HEADER_USERID = "zuul-userid";
  /**
   * 友互通原始token
   */
  public final static String ZUUL_HEADER_YHTTOKEN = "zuul-yht-token";

  /**
   * u8c环境中的serviceCode
   */
  public final static String ZUUL_HEADER_SERVICECODE = "zuul-service-code";
  /**
   * zuul转发-用户code
   */
  public final static String ZUUL_HEADER_USERCODE = "zuul-usercode";
  /**
   * zuul转发-用户名
   */
  public final static String ZUUL_HEADER_USERNAME = "zuul-username";
  /**
   * zuul转发-公司ID
   */
  public final static String ZUUL_HEADER_CORPID = "zuul-corpid";
  /**
   * zuul转发-公司名
   */
  public final static String ZUUL_HEADER_CORPNAME = "zuul-corpname";

  /**
   * @deprecated zuul转发-请求ID,弃用，统一换为REQUEST_ID_KEY
   */
  @Deprecated
  public final static String ZUUL_HEADER_REQID = "zuul-request-id";

  /**
   * @deprecated zuul转发-认证成功 替换为请求网关来源
   */
  @Deprecated
  public final static String ZUUL_HEADER_AUTH_SUCCESS = "zuul-auth-success";

  /**
   * zuul转发-请求网关来源
   */
  public final static String ZUUL_HEADER_GATEWAY_FROM = "zuul-gateway-from";

  /**
   * URL上面的appid
   */
  public final static String PATH_APPID = "appid";

  /**
   * MDC-参数userid
   */
  public static final String CUR_USER_ID_KEY = "current-user-id";

  /**
   * MDC-请求ID
   */
  public static final String REQUEST_ID_KEY = "request-id";

  /**
   * 慢日志常量
   */
  public static final String SLOW_LOGGER = "com.yonyou.einvoice.slow";

  /**
   * gateway记录开始时间
   */
  public static final String TIME_START = "time-start";

  /**
   * 接口版本信息，用于恢复发布版本话
   */
  public static final String TAX_VERSION = "x-tax-version";

  /**
   * 平台管理员角色
   */
  public static final String ROLE_ADMIN = "admin";

  /**
   * pdf发票上传文件数量限制
   */
  public static final int PDF_UPLOAD_NUM = 20;

  /**
   * pdf发票上传单个文件大小限制
   */
  public static final int PDF_FILE_SIZE = 1 * 1024 * 1024;

  public static final String TRACE_ID = "traceId";
}
