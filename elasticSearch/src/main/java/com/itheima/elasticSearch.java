package com.itheima;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.Transport;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname elasticSearch
 * @Description TODO
 * @Date 2019/8/17 17:20
 * @Created by wangshuyuan
 */
public class elasticSearch {
    @Test
    public void createIndex() throws IOException {
        //创建客户端访问对象
        TransportClient transportClient=new PreBuiltTransportClient(Settings.EMPTY).addTransportAddress(
                new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"),9300));
        //构建文档内容
       /* XContentBuilder xc=XContentFactory.jsonBuilder().startObject()
                .field("id",1)
                .field("title","这是title")
                .field("content","content")
                .endObject();*/
       //使用map方式传输数据
        Map map=new HashMap();
        map.put("id",2);
        map.put("title","mapTitle");
        map.put("content","mapTitle");
        //创建索引,类型，文档
        IndexResponse indexResponse=transportClient.prepareIndex("wm","csMap","2").setSource(map).get();
        System.out.println(indexResponse.status());
    }
    @Test
    public void findAll()throws Exception{
        //创建客户端访问对象
        TransportClient transportClient=new PreBuiltTransportClient(Settings.EMPTY).addTransportAddress(
                new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"),9300));
        //构建搜索内容
        SearchResponse response=transportClient.prepareSearch("wm").setTypes("cs")
                .setQuery(QueryBuilders.matchAllQuery()).get();
        //构建搜索结果
        SearchHits hits = response.getHits();
        System.out.println(hits.totalHits);
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }


    }
    //字符串查询
    @Test
    public void testQueryString()throws Exception{
        TransportClient transportClient=new PreBuiltTransportClient(Settings.EMPTY).addTransportAddress(
                new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"),9300));
        //构建搜索内容
        SearchResponse searchResponse=transportClient.prepareSearch("wm").setTypes("cs")
                .setQuery(QueryBuilders.queryStringQuery("这是")).get();
        ///构建搜索结果
             SearchHits hits = searchResponse.getHits();
                System.out.println(hits.totalHits);
                for (SearchHit hit : hits) {
                    System.out.println(hit.getSourceAsString());
                }
                transportClient.close();
    }
    @Test
    //模糊查询
    public void likeSearch() throws UnknownHostException {
        //创建客户端访问对象
        TransportClient transportClient=new PreBuiltTransportClient(Settings.EMPTY).addTransportAddress(
                new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"),9300)
        );
        //创建查询内容
        SearchResponse response = transportClient.prepareSearch("wm").setTypes("csMap").setQuery(
                QueryBuilders.wildcardQuery("title","map*")
        ).get();
        //构建返回结果
        SearchHits hits = response.getHits();
        System.out.println(hits.totalHits);
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }
        transportClient.close();

    }
    //term搜索
    @Test
    public void TermSearch() throws UnknownHostException {
        //创建客户端访问对象
        TransportClient transportClient=new PreBuiltTransportClient(Settings.EMPTY).addTransportAddress(
                new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"),9300)
        );
        //创建查询内容
        SearchResponse response = transportClient.prepareSearch("wm").setTypes("csMap").setQuery(
                QueryBuilders.termQuery("title","mapTitle")
        ).get();
        //构建返回结果
        SearchHits hits = response.getHits();
        System.out.println(hits.totalHits);
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }
        transportClient.close();
    }

}
