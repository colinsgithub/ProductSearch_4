/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package string;

/**
 *
 * @author user
 */
public class StringSearch {

    protected int min_keyword_length;
    protected int min_content_length;

    //main attributes
    protected String keyword;
    protected int keywordLength;
    protected String content;
    protected int contentLength;

    //constructor
    public StringSearch() {
        min_keyword_length = 1;
        min_content_length = 2;
    }

    public StringSearch(int min_keyword_length, int min_content_length) {
        this.min_keyword_length = min_keyword_length;
        this.min_content_length = min_content_length;
    }

    //gettle and settle
    public int getMinKeywordLength() {
        return min_keyword_length;
    }

    public void setMinKeywordLength(int min_keyword_length) {
        this.min_keyword_length = min_keyword_length;
    }

    public int getMinContentLength() {
        return min_content_length;
    }

    public void setMinContentLength(int min_content_length) {
        this.min_content_length = min_content_length;
    }

    //main function
    public boolean search(String keyword, String content) {

        //check
        if (keyword == null || content == null
                || (keywordLength = keyword.length()) < min_keyword_length
                || (contentLength = content.length()) < min_content_length) {
            return false;
        }

        //operation
        for (int i = 0; i < contentLength; i++) {
            for (int j = 0; j < keywordLength; j++) {

            }
        }
        int savePoint;
        for (int i = 0; i < contentLength; i++) {
            savePoint = i;
            for (int j = 0; j < keywordLength; j++) {
                if (i == contentLength
                        || keyword.charAt(j) != content.charAt(i)) {
                    break;
                } else if (j == keywordLength - 1) {
                    return true;
                }
                i++;
            }

            i = savePoint;
        }
        return false;
    }

}
