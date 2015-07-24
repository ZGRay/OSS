package com.example.orm.item;

import java.io.Serializable;

/**
 * Created by meng.jiang on 2014/10/23.
 */
public class OrderQuestionAction implements Serializable {

	public static final int Action_DoNothing = 0;
	public static final int Action_Feedback = 1;
	public static final int Action_NavigatePage = 2;

	private static final long serialVersionUID = 1L;

	public static class QButtonAction implements Serializable {
		public String qactionstr;
		public int qflag;
		public String qactionurl;
		public String qparam;
	}

	public String qid;
	public String qstatus;
	public String qtext;
	public String qmsg;
	public String qtouchurl;
	public QButtonAction[] qbuttons;

	public boolean isStateProcessing() {
		return "2".equals(qstatus);
	}

	public boolean isStateFinished() {
		return "3".equals(qstatus);
	}
}
