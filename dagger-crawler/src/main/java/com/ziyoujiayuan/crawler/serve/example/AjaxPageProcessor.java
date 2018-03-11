package com.ziyoujiayuan.crawler.serve.example;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.JsonPathSelector;
import org.apache.commons.collections.CollectionUtils;

/**
 * 
 * @Author wanghjbuf
 * @Date 2018年3月5日
 */
public class AjaxPageProcessor implements PageProcessor {

    private Site site = Site.me();

    private static final String LIST_URL = "http://angularjs\\.cn/api/article/latest.*";
	
	/* (non-Javadoc)
	 * @see us.codecraft.webmagic.processor.PageProcessor#process(us.codecraft.webmagic.Page)
	 */
	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
        if (page.getUrl().regex(LIST_URL).match()) {
            List<String> ids = new JsonPathSelector("$.data[*]._id").selectList(page.getRawText());
            if (CollectionUtils.isNotEmpty(ids)) {
                for (String id : ids) {
                    page.addTargetRequest("http://angularjs.cn/api/article/" + id);
                }
            }
        } else {
            page.putField("title", new JsonPathSelector("$.data.title").select(page.getRawText()));
            page.putField("content", new JsonPathSelector("$.data.content").select(page.getRawText()));
        }

	}

	/* (non-Javadoc)
	 * @see us.codecraft.webmagic.processor.PageProcessor#getSite()
	 */
	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
        return site;
	}

    public static void main(String[] args) {
        Spider.create(new AjaxPageProcessor()).addPipeline(new JsonFilePipeline("/Users/mac/doc-workspace/webmagic_temp/json")).addUrl("http://angularjs.cn/api/article/latest?p=1&s=20").run();
    }
}
