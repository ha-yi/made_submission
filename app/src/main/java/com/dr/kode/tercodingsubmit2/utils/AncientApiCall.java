package com.dr.kode.tercodingsubmit2.utils;

// keep for record only
@Deprecated()
public class AncientApiCall {
//    private final static String ANIME_LIST = "https://kitsu.io/api/edge/anime?page[limit]=20";
//    private final static String TRENDING_LIST = "https://kitsu.io/api/edge/trending/";
//    private final static String CATEGORY_LIST = "https://kitsu.io/api/edge/categories?page[limit]=50";
//
//    private static final AncientApiCall ourInstance = new AncientApiCall();
//
//    public static AncientApiCall getInstance() {
//        return ourInstance;
//    }
//
//    private AncientApiCall() {
//    }
//
//    public static void getListAnime(Context context, CallHelper<AnimeResponse> callback) {
//        RequestQueue vol = Volley.newRequestQueue(context);
//        StringRequest req = new StringRequest(ANIME_LIST, (response) -> {
//            Log.e("RESPONSE", "SUCCESS : " + response);
//            Type lanimeType = new TypeToken<AnimeResponse>() {}.getType();
//            AnimeResponse data = new Gson().fromJson(response, lanimeType);
//            callback.onData(data);
//        }, (error) -> {
//            Log.e("RESPONSE", "ERROR = " + error.getMessage());
//        });
//        vol.add(req);
//    }
//
//    public static void getListAnime(Context context, String category, int offset, CallHelper<AnimeResponse> callback) {
//        RequestQueue vol = Volley.newRequestQueue(context);
//        String url = ANIME_LIST + "&filter[categories]=" + category + "&page[offset]=" + offset;
//        StringRequest req = new StringRequest(url, (response) -> {
//            Log.e("RESPONSE", "SUCCESS : " + response);
//            Type lanimeType = new TypeToken<AnimeResponse>() {}.getType();
//            AnimeResponse data = new Gson().fromJson(response, lanimeType);
//            callback.onData(data);
//        }, (error) -> {
//            Log.e("RESPONSE", "ERROR = " + error.getMessage());
//        });
//        vol.add(req);
//    }
//
//
//    public static void getCategories(Context context, CallHelper<CategoryResponse> callback) {
//        RequestQueue vol = Volley.newRequestQueue(context);
//        StringRequest req = new StringRequest(CATEGORY_LIST, (response) -> {
//            Log.e("RESPONSE", "SUCCESS : " + response);
//            Type catType = new TypeToken<CategoryResponse>() {}.getType();
//            CategoryResponse data = new Gson().fromJson(response, catType);
//            callback.onData(data);
//        }, (error) -> {
//            Log.e("RESPONSE", "ERROR = " + error.getMessage());
//        });
//        vol.add(req);
//    }
}
