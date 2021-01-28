package com.ae.visuavid.service.dto;

import java.util.ArrayList;
import java.util.List;

public class CategoryNodeDTO implements BaseDTO {
    private String id;
    private String name;
    private String type;
    private List<CategoryNodeDTO> children;

    public CategoryNodeDTO() {
        // Do nothing
    }

    public CategoryNodeDTO(CategoryDTO category) {
        setId(category.getId().toString());
        setName(category.getName());
        setType("category");
        List<SubCategoryDTO> scs = category.getSubCategories();
        if (scs != null && scs.size() > 0) {
            for (SubCategoryDTO sc : scs) {
                this.addChild(sc);
            }
        }
    }

    private void addChild(SubCategoryDTO sc) {
        CategoryNodeDTO sNode = new CategoryNodeDTO();
        sNode.setId(sc.getId().toString());
        sNode.setName(sc.getName());
        sNode.setType("subCategory");
        sNode.setChildren(null);
        if (this.children == null) {
            this.children = new ArrayList<>();
        }
        this.children.add(sNode);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<CategoryNodeDTO> getChildren() {
        return children;
    }

    public void setChildren(List<CategoryNodeDTO> children) {
        this.children = children;
    }
}
