package digytal.desktop.util.utils.http;

import java.util.List;

public class HttpResponse {
	private Response response;
	private HttpResponse() {
		
	}
	public static HttpResponse of(Response response) {
		HttpResponse instance = new HttpResponse();
		instance.response = response;
		return instance;
	}
	public <T>Response<T> body() {
		return response;
	}
	public <T>List<T> list(Class type){
		return Mapper.mapList(body().getBody(), type);
	}
	public <T>List<T> convertList(Class type){
		List list = (List) body().getBody();
		return Mapper.convertList(list, type);
		
	}
	public <T> T one(Class type){
		return Mapper.mapObjet(body().getBody(), type);
	}
}
