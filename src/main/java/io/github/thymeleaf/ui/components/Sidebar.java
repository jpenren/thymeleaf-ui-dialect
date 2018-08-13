package io.github.thymeleaf.ui.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import io.github.thymeleaf.ui.Component;
import io.github.thymeleaf.ui.Element;
import io.github.thymeleaf.ui.elements.Link;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Sidebar extends Component {
    private static final Comparator<SidebarElement> COMPARATOR = new SidebarElementComparator();
    private final List<SidebarElement> entries = new ArrayList<>();
    private String header;

    public Sidebar(String header) {
        this.header = header;
    }

    public Sidebar entry(Entry entry) {
        return add(entry);
    }

    public Sidebar menu(Menu menu) {
        return add(menu);
    }

    public Sidebar add(SidebarElement entry) {
        entries.add(entry);
        return this;
    }

    public List<SidebarElement> getEntries() {
        Collections.sort(entries, COMPARATOR);
        return Collections.unmodifiableList(entries);
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class Entry extends Link implements SidebarElement {
        private final int sort;

        public Entry(String href, String text, int sort) {
            super(href, text);
            this.sort = sort;
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class Menu extends Element implements SidebarElement {
        private final List<SidebarElement> entries = new ArrayList<>();
        private final String id;
        private final String text;
        private final int sort;

        public Menu entry(Entry entry) {
            entries.add(entry);
            return this;
        }

        public boolean isCollapsed(HttpServletRequest request) {
            return false;
        }
        
        public List<SidebarElement> getEntries() {
            Collections.sort(entries, COMPARATOR);
            return Collections.unmodifiableList(entries);
        }

    }

    private interface SidebarElement {
        int getSort();
    }
    
    private static class SidebarElementComparator implements Comparator<SidebarElement> {
        
        @Override
        public int compare(SidebarElement e1, SidebarElement e2) {
            return Integer.compare(e1.getSort(), e2.getSort());
        }
    }
    
}
