import { Component,OnInit, ViewChild} from '@angular/core';
import { MatTreeFlatDataSource, MatTreeFlattener } from '@angular/material/tree';
import { MatMenuTrigger } from '@angular/material/menu';
import { of as observableOf } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { FlatTreeControl } from '@angular/cdk/tree';
import { CategoryService } from 'app/category/category.service';
import { CategoryNode } from 'app/category/category.model';
import { CatTreeActionComponent, CatTreeActionConfig } from './dialogs/cattreeaction/cattreeaction.component';


export interface TreeNode {
  name: string;
  type: string;
  level: number;
  expandable: boolean;
  id: string;
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
      id: node.id,
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
  onContextItemSelect(actionId: any): void {
     console.log('evtId',actionId);
     const node = this.contextMenu.menuData.node;
     if(actionId==='renameCategory' || actionId==='renameSubCategory'){
        const dialog: CatTreeActionConfig = {
          name: node.name,
          type: node.type,
          id: node.id,
          actionId
        };
        const dialogRef = this.dialog.open(CatTreeActionComponent, { width: '287px', data: dialog });
        dialogRef.afterClosed().subscribe(result => {
          if(result && result !== node.name){
            this.categoryService.rename(node.id,node.type,result).subscribe(resp => {
              console.log(resp);
              node.name = result;
              this.dataSource.data.push(node);
            })
          }
        });
     } else if(actionId ==='createSubCategory'){
        const dialog: CatTreeActionConfig = {
          name: node.name,
          type: node.type,
          id: node.id,
          actionId
        };
        const dialogRef = this.dialog.open(CatTreeActionComponent, { width: '287px', data: dialog });
        dialogRef.afterClosed().subscribe(result => {
          if(result){
            this.categoryService.createSubCategory(node.id,result).subscribe(resp => {
              const n = {
                    name: result,
                    type: 'subCategory',
                    id: resp.id,
                    expandable: false,
                    level: 1
              }
              console.log('n',n);
              const data = this.dataSource.data;
              data.forEach(cNode => {
                  if(cNode.id === node.id){
                    if(!cNode.children){
                      cNode.children = [];
                    }
                    cNode.children.push(n)
                  }
              })
              this.dataSource.data = data;
              this.treeControl.expand(node);
            });
          }
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
    const dialog: CatTreeActionConfig = {
          actionId: 'createCategory'
        };
        const dialogRef = this.dialog.open(CatTreeActionComponent, { width: '287px', data: dialog });
        dialogRef.afterClosed().subscribe(result => {
          if(result){
            this.categoryService.createCategory(result).subscribe(resp => {
              const n = {
                    name: result,
                    type: 'category',
                    id: resp.id,
                    expandable: true,
                    level: 0
              }
              console.log('n',n);
              const data = this.dataSource.data;
              data.push(n);
              this.dataSource.data = data;
            });
          }
        });
 }
}
