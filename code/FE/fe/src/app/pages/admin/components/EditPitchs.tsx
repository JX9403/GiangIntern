
import React, { useState, useEffect } from 'react';
import { TreeTable } from 'primereact/treetable';
import { Column } from 'primereact/column';
import { TreeNode } from 'primereact/treenode';

export default function BasicDemo() {
    const [nodes, setNodes] = useState<TreeNode[]>([]);



    return (
        <div className="card">
            {/* <TreeTable value={nodes} tableStyle={{ minWidth: '50rem' }}>
                <Column field="name" header="Name" expander></Column>
                <Column field="size" header="Size"></Column>
                <Column field="type" header="Type"></Column>
            </TreeTable> */}
        </div>
    );
}
