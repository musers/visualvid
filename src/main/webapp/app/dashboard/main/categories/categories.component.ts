import { Component,OnInit } from '@angular/core';
import { MatTreeFlatDataSource, MatTreeFlattener } from '@angular/material/tree';
import { of as observableOf } from 'rxjs';
import { FlatTreeControl } from '@angular/cdk/tree';
import { CategoryService } from 'app/category/category.service';
import { CategoryNode } from 'app/category/category.model';

export interface TreeNode {
  name: string;
  type: string;
  level: number;
  expandable: boolean;
}

@Component({
  selector: 'jhi-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['categories.scss'],
})
export class DashboardCategoriesComponent implements OnInit{
  /** The TreeControl controls the expand/collapse state of tree nodes.  */
  treeControl: FlatTreeControl<TreeNode>;

  /** The TreeFlattener is used to generate the flat list of items from hierarchical data. */
  treeFlattener: MatTreeFlattener<CategoryNode, TreeNode>;

  /** The MatTreeFlatDataSource connects the control and flattener to provide data. */
  dataSource: MatTreeFlatDataSource<CategoryNode, TreeNode>;

  constructor(private categoryService: CategoryService) {
    this.treeFlattener = new MatTreeFlattener(
      this.transformer,
      this.getLevel,
      this.isExpandable,
      this.getChildren);

    this.treeControl = new FlatTreeControl<TreeNode>(this.getLevel, this.isExpandable);
    this.dataSource = new MatTreeFlatDataSource(this.treeControl, this.treeFlattener);
//     this.dataSource.data = catTree;
  }
   ngOnInit(): void {
    this.categoryService.getCatTree().subscribe(resp => {
       this.dataSource.data = resp;
    })
   }
  /** Transform the data to something the tree can read. */
  transformer(node: CategoryNode, level: number): any {
    return {
      name: node.name,
      type: node.type,
      level,
      expandable: !!node.children
    };
  }

 /** Get the level of the node */
  getLevel(node: TreeNode): any {
    return node.level;
  }

  /** Return whether the node is expanded or not. */
  isExpandable(node: TreeNode): any {
    return node.expandable;
  };

  /** Get the children for the node. */
  getChildren(node: CategoryNode): any {
    return observableOf(node.children);
  }

  /** Get whether the node has children or not. */
  hasChild(index: number, node: TreeNode): any{
    return node.expandable;
  }

}
