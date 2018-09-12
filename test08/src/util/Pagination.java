package util;

import dao.EmployeeDao;

public class Pagination {
	private int ye;
	public void setYe(int ye) {
		this.ye = ye;
	}

	private int yes;
	private int start;
	private int end;
	private int begin;
	public Pagination(int ye, int count, int numInPage, int numOfPage) {
		this.ye=ye;
		if (this.ye < 1) {
			this.ye = 1;
		}

		EmployeeDao empDao = new EmployeeDao();

		yes = 0;
		if (count % numInPage == 0) {
			yes = count / numInPage;
		} else {
			yes = count / numInPage + 1;
		}
		if (this.ye > yes) {
			this.ye = yes;
		}
		start = 0;
		end = 0;
		if(yes<numOfPage) {
			start=1;
			end= yes;
		} else if (this.ye > numOfPage / 2 && this.ye < yes - numOfPage / 2 + 1) {
			start = this.ye - numOfPage / 2;
			end = this.ye + numOfPage / 2;
		} else if (this.ye <= numOfPage / 2) {
			start = 1;
			end = numOfPage;
		} else if (this.ye >= yes - numOfPage + 1) {
			start = yes - numOfPage + 1;
			end = yes;
		}
		begin = (this.ye - 1) * numInPage;

		
	}
	public int getBegin() {
		return begin;
	}

	public int getYe() {
		return ye;
	}
	public int getYes() {
		return yes;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}
}
