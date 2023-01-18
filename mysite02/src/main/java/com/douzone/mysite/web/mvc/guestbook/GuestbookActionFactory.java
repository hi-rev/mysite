package com.douzone.mysite.web.mvc.guestbook;

import com.douzone.mysite.web.mvc.main.MainAction;
import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class GuestbookActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if ("list".equals(actionName)) {
			action = new GuestbookAction();
		} else {
			action = new MainAction();
		}
		
		return action;
	}

}
