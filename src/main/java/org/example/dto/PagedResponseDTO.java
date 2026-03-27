package org.example.dto;

import java.util.List;

public class PagedResponseDTO<T> {

    private List<T> data;
    private Meta meta;

    public PagedResponseDTO() {}

    public PagedResponseDTO(List<T> data, Meta meta) {
        this.data = data;
        this.meta = meta;
    }

    public List<T> getData()           { return data; }
    public void setData(List<T> data)  { this.data = data; }

    public Meta getMeta()              { return meta; }
    public void setMeta(Meta meta)     { this.meta = meta; }

    public static class Meta {

        private long total;
        private int page;
        private int totalPages;

        public Meta() {}

        public Meta(long total, int page, int totalPages) {
            this.total = total;
            this.page = page;
            this.totalPages = totalPages;
        }

        public long getTotal()                 { return total; }
        public void setTotal(long total)       { this.total = total; }

        public int getPage()               { return page; }
        public void setPage(int page)      { this.page = page; }

        public int getTotalPages()                     { return totalPages; }
        public void setTotalPages(int totalPages)      { this.totalPages = totalPages; }
    }
}