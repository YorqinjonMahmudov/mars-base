package uz.me.marsbase.controller.router;


import lombok.Getter;

import static uz.me.marsbase.controller.command.navigation.PageNavigation.HOME;


@Getter
public class Router {

    private String page = HOME;

    private PageChangeType type = PageChangeType.FORWARD;

    public enum PageChangeType {
        FORWARD, REDIRECT;
    }

    public Router() {
    }

    public Router(String page, PageChangeType type) {
        this.page = (page != null ? page : HOME);
        this.type = (type != null ? type : PageChangeType.FORWARD);
    }

    public Router(String page) {
        this.page = page != null ? page : HOME;
    }

    public void setPage(String page) {
        this.page = (page != null ? page : HOME);
    }

    public void setRedirect() {
        this.type = PageChangeType.REDIRECT;
    }

    public void setForward() {
        this.type = PageChangeType.FORWARD;
    }

}
