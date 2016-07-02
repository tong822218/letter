package com.zm.taobao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.ResolutionSyntax;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import sun.util.logging.resources.logging;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.FileItem;
import com.taobao.api.TaobaoClient;
import com.taobao.api.internal.util.StringUtils;
import com.taobao.api.request.ItemSellerGetRequest;
import com.taobao.api.request.ItemUpdateRequest;
import com.taobao.api.request.ItemsInventoryGetRequest;
import com.taobao.api.request.ItemsOnsaleGetRequest;
import com.taobao.api.request.PictureGetRequest;
import com.taobao.api.request.PictureUploadRequest;
import com.taobao.api.request.TopAuthTokenRefreshRequest;
import com.taobao.api.request.TraderatesGetRequest;
import com.taobao.api.request.TradesSoldGetRequest;
import com.taobao.api.request.UserSellerGetRequest;
import com.taobao.api.response.ItemSellerGetResponse;
import com.taobao.api.response.ItemUpdateResponse;
import com.taobao.api.response.ItemsInventoryGetResponse;
import com.taobao.api.response.ItemsOnsaleGetResponse;
import com.taobao.api.response.PictureGetResponse;
import com.taobao.api.response.PictureUploadResponse;
import com.taobao.api.response.TopAuthTokenRefreshResponse;
import com.taobao.api.response.TraderatesGetResponse;
import com.taobao.api.response.TradesSoldGetResponse;
import com.taobao.api.response.UserSellerGetResponse;
import com.zm.model.User;

/**
 * @author 贾尧尧
 *
 */
public class API {

	protected static String url = "http://gw.api.taobao.com/router/rest";
	protected static String appkey = "23293480";
	protected static String secret = "7a2f7cac57e40e0f570b7bf54bdd41d5";

	/**
	 * /**
	 * 
	 * @return taobao.user.seller.get (查询卖家用户信息)
	 *         {"user_seller_get_response":{"user"
	 *         :{"nick":"j11016","user_id":366426558
	 *         },"request_id":"11ifit8dw14fd"}}
	 */
	public static User getCurrentUser() {
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		UserSellerGetRequest req = new UserSellerGetRequest();
		req.setFields("user_id,nick");
		UserSellerGetResponse response = null;
		try {
			response = client.execute(req, Token.get());
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LogFactory.getLog(API.class)
					.error("getCurrentUser错误message" + e.getMessage());
		}

		JSONObject job = JSONObject.fromObject(response.getBody());
		JSONObject items = job.getJSONObject("user_seller_get_response");
		JSONObject item = items.getJSONObject("user");
		User user = new User();
		System.out.println(item.getString("nick") + "==" + item.get("nick").toString());
		user.setName(item.get("nick").toString());
		user.setId(item.get("user_id").toString());
		return user;
	}

}
