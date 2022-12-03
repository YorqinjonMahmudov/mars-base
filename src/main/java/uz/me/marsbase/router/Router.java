package uz.me.marsbase.router;


import lombok.Getter;

import static uz.me.marsbase.command.navigation.PageNavigation.DEFAULT;


@Getter
public class Router {

    private String page = DEFAULT;

    private PageChangeType type = PageChangeType.FORWARD;

    public enum PageChangeType {
        FORWARD, REDIRECT;
    }

    public Router() {
    }

    public Router(String page, PageChangeType type) {
        this.page = (page != null ? page : DEFAULT);
        this.type = (type != null ? type : PageChangeType.FORWARD);
    }

    public Router(String page) {
        this.page = page != null ? page : DEFAULT;
    }

    public void setPage(String page) {
        this.page = (page != null ? page : DEFAULT);
    }

    public void setRedirect() {
        this.type = PageChangeType.REDIRECT;
    }

    public void setForward() {
        this.type = PageChangeType.FORWARD;
    }

}
