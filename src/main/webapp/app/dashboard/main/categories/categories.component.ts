import { Component,OnInit, ViewChild} from '@angular/core';
import { MatTreeFlatDataSource, MatTreeFlattener } from '@angular/material/tree';
import { MatMenuTrigger } from '@angular/material/menu';
import { of as observableOf } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { FlatTreeControl } from '@angular/cdk/tree';
import { CategoryService } from 'app/category/category.service';
import { CategoryNode } from 'app/category/category.model';
import { RenameCategoryComponent, DialogConfig } from './dialogs/renamecategory.component';


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

  @ViewChild(MatMenuTrigger)
  contextMenu: MatMenuTrigger;

  contextMenuPosition = { x: '0px', y: '0px' };
  contextMenuType = 'category';

  constructor(private categoryService: CategoryService, private dialog: MatDialog) {
    this.treeFlattener = new MatTreeFlattener(
      this.transformer,
      this.getLevel,
      this.isExpandable,
      this.getChildren);

    this.treeControl = new FlatTreeControl<TreeNode>(this.getLevel, this.isExpandable);
    this.dataSource = new MatTreeFlatDataSource(this.treeControl, this.treeFlattener);
  }
   ngOnInit(): void {
    this.categoryService.getCategoryTree().subscribe(resp => {
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
  onContextItemSelect(evtId: any): void {
     console.log('evtId',evtId);
     if(evtId==='renameCategory'){
        const dialog: DialogConfig = {
          categoryName: '',
          content: 'Name of the category'
        };
        const dialogRef = this.dialog.open(RenameCategoryComponent, { width: '287px', data: dialog });
        dialogRef.afterClosed().subscribe(result => {
          // TODO backedn service call
          this.contextMenu.menuData.node.name='adf';
          this.dataSource.data.push(this.contextMenu.menuData.node);
        });
     }
  }
  openContextMenu(event: MouseEvent, node: any): void{
    event.preventDefault();
    this.contextMenuType = node.type;
    this.contextMenuPosition.x = event.clientX + 'px';
    this.contextMenuPosition.y = event.clientY + 'px';
    this.contextMenu.menuData = { node };
    this.contextMenu.openMenu();
  }
 addCateogry(): void {
    console.log('addCateogry');
 }
}
