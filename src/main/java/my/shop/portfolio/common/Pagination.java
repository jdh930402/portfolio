package my.shop.portfolio.common;

public class Pagination {
	private int page = 1, start, length, pageLength, totalPage, startPage, endPage, pageNum, totalRows, currentBlock,
			totalBlock;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int page, int length) {
		this.start = (page - 1) * length;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getPageLength() {
		return pageLength;
	}

	public void setPageLength(int pageLength) {
		this.pageLength = pageLength;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalRows, int length) {
		int totalPage = totalRows % length == 0 ? totalRows / length : totalRows / length + 1;
		if (totalPage == 0) {
			totalPage = 1;
		}
		this.totalPage = totalPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int currentBlock, int pageLength) {
		this.startPage = 1 + (currentBlock - 1) * pageLength;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int currentBlock, int pageLength) {
		int endPage = pageLength + (currentBlock - 1) * pageLength;
		if (currentBlock == totalBlock) {
			endPage = totalPage;
		}
		this.endPage = endPage;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int totalRows, int page, int length) {
		this.pageNum = totalRows + (page - 1) * (-length);
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getCurrentBlock() {
		return currentBlock;
	}

	public void setCurrentBlock(int page, int pageLength) {
		this.currentBlock = page % pageLength == 0 ? page / pageLength : page / pageLength + 1;
	}

	public int getTotalBlock() {
		return totalBlock;
	}

	public void setTotalBlock(int totalPage, int pageLength) {
		this.totalBlock = totalPage % pageLength == 0 ? totalPage / pageLength : totalPage / pageLength + 1;
	}

	public void paginationSetting(int page, int length, int pageLength, int totalRows) {
		setPage(page);
		setLength(length);
		setPageLength(pageLength);
		setStart(page, length);
		setTotalRows(totalRows);
		setPageNum(totalRows, page, length);
		setTotalPage(totalRows, length);
		setCurrentBlock(page, pageLength);
		setTotalBlock(totalPage, pageLength);
		setStartPage(currentBlock, pageLength);
		setEndPage(currentBlock, pageLength);
	}
}
