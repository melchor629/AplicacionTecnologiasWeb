package beans;

import org.commonmark.ext.autolink.AutolinkExtension;
import org.commonmark.ext.gfm.strikethrough.StrikethroughExtension;
import org.commonmark.node.BlockQuote;
import org.commonmark.node.Image;
import org.commonmark.node.ListBlock;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.NodeRenderer;
import org.commonmark.renderer.html.HtmlNodeRendererContext;
import org.commonmark.renderer.html.HtmlRenderer;
import org.commonmark.renderer.html.HtmlWriter;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Parsea markdown
 * @author Melchor Alejo Garau Madrigal
 * @see <a href="https://stackoverflow.com/questions/14172764/jsf-application-scope-instantiation-and-injection">A StackOverflow answer</a>
 */
@ApplicationScoped
@ManagedBean
public class MarkdownParserBean {
    private Parser parser;
    private HtmlRenderer renderer;

    @PostConstruct
    public void init() {
        parser = Parser.builder()
                .extensions(Arrays.asList(AutolinkExtension.create(), StrikethroughExtension.create()))
                .enabledBlockTypes(new HashSet<>(Arrays.asList(BlockQuote.class, ListBlock.class)))
                .build();
        renderer = HtmlRenderer.builder()
                .extensions(Collections.singleton(StrikethroughExtension.create()))
                .nodeRendererFactory(ImageRemover::new)
                .build();
    }

    /**
     * Convierte un texto en Markdown a HTML
     * @param md Markdown
     * @return HTML
     */
    public String parse(String md) {
        return renderer.render(parser.parse(md));
    }

    /**
     * Permite eliminar las imágenes del Markdown
     */
    private static class ImageRemover implements NodeRenderer {
        private final HtmlWriter html;

        private ImageRemover(HtmlNodeRendererContext context) {
            this.html = context.getWriter();
        }

        @Override
        public Set<Class<? extends Node>> getNodeTypes() {
            return Collections.singleton(Image.class);
        }

        @Override
        public void render(Node node) {
            html.raw("<!-- img -->");
        }
    }
}
