/**
 * 
 */
package com.salesmanager.shop.store.model.paging;

import java.io.Serializable;

/**
 *  POJO representation of pagination
 * @author Umesh Awasthi
 *
 */
public class PaginationData implements Serializable
{
    
    
    private static final long serialVersionUID = 1L;

    /** The number of results per page.*/
    private int pageSize;
    private int currentPage;
    private int offset ;
    private int totalCount;
    private int totalPages;
    private int countByPage;

    
    public PaginationData(int pageSize,int currentPage) {
        if (pageSize == 0)
            throw new IllegalArgumentException("limit cannot be 0 for pagination.");

       
        this.pageSize = pageSize;
        this.currentPage=currentPage;
    }
    
    
    public int getPageSize()
    {
        return pageSize;
    }

    
    /**
    * The current page number this pagination object represents
    *
    * @return the page number
    */
    public int getPageNumber() {
        if (offset < pageSize || pageSize == 0)
            return 1;

        return (offset / pageSize) + 1;
    }
    
    
    /**
    * The offset for this pagination object. The offset determines what index (0 index) to start retrieving results from.
    *
    * @return the offset
    */
        public int getOffset() {
            return (currentPage - 1) * pageSize + 1;
        }
        
     
    /**
     * Creates a new pagination object representing the next page
     * 
     * @return new pagination object with offset shifted by offset+limit
     */
    public PaginationData getNext()
    {
        return new PaginationData( offset + pageSize, pageSize );
    }
    
    
    /**
    * Creates a new pagination object representing the previous page
    *
    * @return new pagination object with offset shifted by offset-limit
    */
        public PaginationData getPrevious() {
            if (pageSize >= offset) {
                return new PaginationData(0, pageSize);
            } else {
                return new PaginationData(offset - pageSize, pageSize);
            }
        }


    public int getCurrentPage()
    {
        return currentPage;
    }


    public void setCurrentPage( int currentPage )
    {
        this.currentPage = currentPage;
    }


    public int getTotalCount()
    {
        return totalCount;
    }


    public void setTotalCount( int totalCount )
    {
        this.totalCount = totalCount;
    }


    public int getTotalPages()
    {
        
        Integer totalPages= Integer.valueOf((int) (Math.ceil(Integer.valueOf(totalCount).doubleValue() / pageSize)));
        return totalPages;
    }


	public int getCountByPage() {
		return countByPage;
	}


	public void setCountByPage(int countByPage) {
		this.countByPage = countByPage;
	}


	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

   
    
        
    
    
}
