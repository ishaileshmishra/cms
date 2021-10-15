package com.contentstack.sdk;

import com.contentstack.sdk.callback.ResultCallBack;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

public class Entry extends Execute {

    private String contentTypeName = null;
    private HashMap<String, String> stackHeader = null;
    protected LinkedHashMap<String, Object> formHeader = null;
    protected HashMap<String, Object> owner = null;
    protected HashMap<String, Object> _metadata = null;

    private ContentType contentTypeInstance = null;
    private String[] tags = null;
    protected String uid = null;
    protected JSONObject resultJson = null;
    protected String ownerEmailId = null;
    protected String ownerUid = null;
    protected String title = null;
    protected String url = null;
    protected String language = null;

    private JSONArray referenceArray;
    public JSONObject otherPostJSON;
    private JSONArray objectUidForOnly;
    private JSONArray objectUidForExcept;
    private JSONObject onlyJsonObject;
    private JSONObject exceptJsonObject;

    private String rteContent = null;

    private Entry() {
    }

    protected Entry(
            @NotNull String contentTypeName,
            @NotNull HashMap<String, String> headerMap) {
        this.contentTypeName = contentTypeName;
        this.stackHeader = headerMap;
        this.otherPostJSON = new JSONObject();
    }


    public void setHeader(String key, String value) {
        if (!key.isEmpty() && !value.isEmpty()) {
            this.stackHeader.put(key, value);
        }
    }


    public void removeHeader(String key) {
        if (!key.isEmpty()) {
            this.stackHeader.remove(key);
        }
    }


    public Entry setLocale(String locale) {
        if (locale != null) {
            try {
                otherPostJSON.put("locale", locale);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return this;
    }


    public Entry except(String[] fieldUid){
        try{
            if(fieldUid != null && fieldUid.length > 0){

                if(objectUidForExcept == null){
                    objectUidForExcept = new JSONArray();
                }

                int count = fieldUid.length;
                for(int i = 0; i < count; i++){
                    objectUidForExcept.put(fieldUid[i]);
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return this;
    }


    public Entry includeReference(String referenceField) {
        try {
            if (!referenceField.isEmpty()) {
                if (referenceArray == null) {
                    referenceArray = new JSONArray();
                }
                referenceArray.put(referenceField);
                otherPostJSON.put("include[]", referenceArray);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }


    public Entry includeReference(String[] referenceFields) {
        try {
            if (referenceFields != null && referenceFields.length > 0) {
                if (referenceArray == null) {
                    referenceArray = new JSONArray();
                }
                for (int i = 0; i < referenceFields.length; i++) {
                    referenceArray.put(referenceFields[i]);
                }
                otherPostJSON.put("include[]", referenceArray);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }


    public Entry only(String[] fieldUid) {
        try{
            if (fieldUid != null && fieldUid.length > 0) {
                if(objectUidForOnly == null){
                    objectUidForOnly = new JSONArray();
                }

                int count = fieldUid.length;
                for(int i = 0; i < count; i++){
                    objectUidForOnly.put(fieldUid[i]);
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return this;
    }


    public Entry onlyWithReferenceUid(ArrayList<String> fieldUid, String referenceFieldUid){
        try{
            if(fieldUid != null && referenceFieldUid != null){
                if(onlyJsonObject == null){
                    onlyJsonObject = new JSONObject();
                }
                JSONArray fieldValueArray = new JSONArray();
                int count = fieldUid.size();
                for(int i = 0; i < count; i++){
                    fieldValueArray.put(fieldUid.get(i));
                }

                onlyJsonObject.put(referenceFieldUid, fieldValueArray);
                includeReference(referenceFieldUid);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return this;
    }


    public Entry exceptWithReferenceUid(ArrayList<String> fieldUid, String referenceFieldUid){
        try{
            if(fieldUid != null && referenceFieldUid != null){
                if(exceptJsonObject == null){
                    exceptJsonObject = new JSONObject();
                }
                JSONArray fieldValueArray = new JSONArray();
                int count = fieldUid.size();
                for(int i = 0; i < count; i++){
                    fieldValueArray.put(fieldUid.get(i));
                }

                exceptJsonObject.put(referenceFieldUid, fieldValueArray);
                includeReference(referenceFieldUid);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return this;
    }


    protected void setTags(String[] tags) {
        this.tags = tags;
    }


    protected void setUid(String uid) {
        this.uid = uid;
    }


    public void fetch(ResultCallBack callBack) {
//        try {
//            if (!uid.isEmpty()) {
//                String URL = "/" + contentTypeInstance.stackInstance.VERSION + "/content_types/" + contentTypeName + "/entries/" + uid;
//                LinkedHashMap<String, Object> headers = getHeader(localHeader);
//                LinkedHashMap<String, String> headerAll = new LinkedHashMap<String, String>();
//                JSONObject urlQueries = new JSONObject();
//                if (headers != null && headers.size() > 0) {
//                    for (Map.Entry<String, Object> entry : headers.entrySet()) {
//                        headerAll.put(entry.getKey(), (String) entry.getValue());
//                    }
//                    if(headers.containsKey("environment")){
//                        urlQueries.put("environment", headers.get("environment"));
//                    }
//                }
//                fetchFromNetwork(URL, urlQueries, callBack);
//            }
//        }catch(Exception e) {
//            // TODO: Exception callback
//        }
    }


    private void fetchFromNetwork(String URL, JSONObject urlQueries, ResultCallBack callBack) {
//        try{
//
//            JSONObject mainJson = new JSONObject();
//            setIncludeJSON(urlQueries, callBack);
//            mainJson.put("query", urlQueries);
//            mainJson.put("_method", Constants.RequestMethod.GET.toString());
//            HashMap<String, Object> urlParams = getUrlParams(mainJson);
//            new CSBackgroundTask(this, contentTypeInstance.stackInstance, CSController.FETCHENTRY, URL, getHeader(localHeader), urlParams, new JSONObject(), Constants.callController.ENTRY.toString(), false, Constants.RequestMethod.GET, callBack);
//
//        }catch(Exception e){
//            throwException(null, e, callBack);
//        }
    }


    private LinkedHashMap<String, Object> getUrlParams(JSONObject jsonMain) {

        JSONObject queryJSON = jsonMain.optJSONObject("query");
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<>();

        if (queryJSON != null && queryJSON.length() > 0) {
            Iterator<String> iter = queryJSON.keys();
            while (iter.hasNext()) {
                String key = iter.next();
                try {
                    Object value = queryJSON.opt(key);
                    hashMap.put(key, value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return hashMap;
        }

        return null;
    }


    private void setIncludeJSON(JSONObject mainJson, ResultCallBack callBack){
        try {
            Iterator<String> iterator = otherPostJSON.keys();
            while (iterator.hasNext()) {
                String key = iterator.next();
                Object value = otherPostJSON.get(key);
                mainJson.put(key, value);
            }

            if(objectUidForOnly!= null && objectUidForOnly.length() > 0){
                mainJson.put("only[BASE][]", objectUidForOnly);
                objectUidForOnly = null;
            }

            if(objectUidForExcept != null && objectUidForExcept.length() > 0){
                mainJson.put("except[BASE][]", objectUidForExcept);
                objectUidForExcept = null;
            }

            if(exceptJsonObject != null && exceptJsonObject.length() > 0){
                mainJson.put("except", exceptJsonObject);
                exceptJsonObject = null;
            }

            if(onlyJsonObject != null && onlyJsonObject.length() > 0){
                mainJson.put("only", onlyJsonObject);
                onlyJsonObject = null;
            }
        }catch(Exception e){
            //TODO: make Error callback
        }
    }


    private LinkedHashMap<String, Object> getHeader(LinkedHashMap<String, Object> localHeader) {
        LinkedHashMap<String, Object> mainHeader = formHeader;
        LinkedHashMap<String, Object> classHeaders = new LinkedHashMap<>();
        if(localHeader != null && localHeader.size() > 0){
            if(mainHeader != null && mainHeader.size() > 0) {
                for (Map.Entry<String, Object> entry : localHeader.entrySet()) {
                    String key = entry.getKey();
                    classHeaders.put(key, entry.getValue());
                }
                for (Map.Entry<String, Object> entry : mainHeader.entrySet()) {
                    String key = entry.getKey();
                    if(!classHeaders.containsKey(key)) {
                        classHeaders.put(key, entry.getValue());
                    }
                }
                return classHeaders;
            }else{
                return localHeader;
            }
        }else{
            return formHeader;
        }
    }


    public Entry addParam(String key, String value){

        if(key != null && value != null) {
            try {
                otherPostJSON.put(key, value);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return this;
    }


    public Entry includeReferenceContentTypeUID(){
        try {
            otherPostJSON.put("include_reference_content_type_uid", "true");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }


    public Entry includeContentType(){
        try {
            if (otherPostJSON.has("include_schema")){
                otherPostJSON.remove("include_schema");
            }
            otherPostJSON.put("include_content_type",true);
            otherPostJSON.put("include_global_field_schema",true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }


    public Entry includeFallback(){
        otherPostJSON.put("include_fallback", true);
        return this;
    }


    public Entry includeEmbeddedItems() {
        otherPostJSON.put("include_embedded_items[]", "BASE");
        return this;
    }


    public Entry includeBranch() {
        otherPostJSON.put("include_branch", true);
        return this;
    }

}
