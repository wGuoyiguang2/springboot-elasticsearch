package springboot.elasticsearch.service;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BookService {

    private String indexName = "book"; //相当于数据库名称
    private String indexType = "novel";	//相当于数据表名称

    @Autowired
    private TransportClient client;

    public GetResponse getById(String id){
        return this.client.prepareGet(indexName,indexType,id).get();
    }

//    public GetResponse getByAuthorName(String authorName){
//        return this.client.prepareSearch()
//    }

    public IndexResponse add(String title, String author, int wordCount, String publishDate) throws Exception {

        XContentBuilder content = XContentFactory.jsonBuilder()
                .startObject()
                .field("title",title)
                .field("author",author)
                .field("publish_date",publishDate)
                .field("word_count",wordCount)
                .endObject();

        IndexResponse response = this.client.prepareIndex(indexName,indexType)
                .setSource(content)
                .get();

        return response;

    }

    public DeleteResponse remove(String id) {
       return this.client.prepareDelete(indexName,indexType,id).get();
    }

    public UpdateResponse modify(String id, String title, String author) throws Exception {
        UpdateRequest request = new UpdateRequest(indexName,indexType,id);

        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject();

        if(title != null){
            builder.field("title",title);
        }
        if(author != null){
            builder.field("author",author);
        }
        builder.endObject();

        request.doc(builder);
        return this.client.update(request).get();
    }
}
