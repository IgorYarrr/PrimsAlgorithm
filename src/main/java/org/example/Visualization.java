package org.example;

import guru.nidi.graphviz.attribute.*;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static guru.nidi.graphviz.attribute.Attributes.attr;
import static guru.nidi.graphviz.model.Factory.*;
import static guru.nidi.graphviz.model.Link.to;

public class Visualization {
    private int[][] matrix;
    private int[] result_nodes;
    public String Guid = UUID.randomUUID().toString();
    public String GHuid = UUID.randomUUID().toString();


    public Visualization(int[][] m, int[] result_nodes)  {
        matrix = m;
        this.result_nodes = result_nodes;
        MakePicture();
        MakePictureHighlighted();
    }

    private void MakePicture() {
        MutableGraph g = mutGraph("graph")
                .graphAttrs().add(Rank.dir(Rank.RankDir.LEFT_TO_RIGHT));
        g = AddNodes(g);
        try {
            Graphviz.fromGraph(g).height(400).render(Format.PNG).toFile(new File("src/main/resources/GraphPictures/"+Guid+".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void MakePictureHighlighted(){
        MutableGraph gh = mutGraph("graph highlighted")
                .graphAttrs().add(Rank.dir(Rank.RankDir.LEFT_TO_RIGHT));
        gh = AddNodesHighlighted(gh);
        try {
            Graphviz.fromGraph(gh).height(400).render(Format.PNG).toFile(new File("src/main/resources/GraphPictures/"+GHuid+".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private MutableGraph AddNodes(MutableGraph g){
        for(int i = 0; i < matrix.length; i++){
            for(int j = i + 1; j < matrix.length; j++){
                if(matrix[i][j] != 0)
                    g.add(mutNode(Integer.toString(i)).addLink(to(mutNode(Integer.toString(j))).add(attr("label", matrix[i][j]))));
            }
        }
        return g;
    }

    private MutableGraph AddNodesHighlighted(MutableGraph gh){
        for(int i = 0; i < matrix.length; i++){
            for(int j = i + 1; j < matrix.length; j++){
                if(matrix[i][j] != 0) {
                    if (result_nodes[i] == j || result_nodes[j] == i) {
                        gh.add(mutNode(Integer.toString(i))
                                .addLink(to(mutNode(Integer.toString(j))).add(attr("label", matrix[i][j])).add(attr("color", "red"))));
                    }
                    else {
                        gh.add(mutNode(Integer.toString(i))
                                .addLink(to(mutNode(Integer.toString(j))).add(attr("label", matrix[i][j]))));
                    }
                }
            }
        }
        return gh;
    }
}
