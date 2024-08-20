
// import React, { useEffect, useState } from 'react';
// import { DataTable, DataTableRowEditCompleteEvent } from 'primereact/datatable';
// import { Column, ColumnEditorOptions } from 'primereact/column';
// import { InputText } from 'primereact/inputtext';
// import { InputNumber, InputNumberValueChangeEvent } from 'primereact/inputnumber';
// import { Dropdown, DropdownChangeEvent } from 'primereact/dropdown';
// import { Tag } from 'primereact/tag';
// import { ProductService } from './service/ProductService';

// interface Product {
//     id: string;
//     code: string;
//     name: string;
//     description: string;
//     image: string;
//     price: number;
//     category: string;
//     quantity: number;
//     inventoryStatus: string;
//     rating: number;
// }

// export default function RowEditingDemo() {
//     const [products, setProducts] = useState<Product[]>();
//     const [statuses] = useState<string[]>(['INSTOCK', 'LOWSTOCK', 'OUTOFSTOCK']);

//     useEffect(() => {
//         ProductService.getProductsMini().then((data) => setProducts(data));
//     }, []);

//     const getSeverity = (value: string) => {
//         switch (value) {
//             case 'INSTOCK':
//                 return 'success';

//             case 'LOWSTOCK':
//                 return 'warning';

//             case 'OUTOFSTOCK':
//                 return 'danger';

//             default:
//                 return null;
//         }
//     };

//     const onRowEditComplete = (e: DataTableRowEditCompleteEvent) => {
//         let _products = [...products];
//         let { newData, index } = e;

//         _products[index] = newData as Product;

//         setProducts(_products);
//     };

//     const textEditor = (options: ColumnEditorOptions) => {
//         return <InputText type="text" value={options.value} onChange={(e: React.ChangeEvent<HTMLInputElement>) => options.editorCallback!(e.target.value)} />;
//     };

//     const statusEditor = (options: ColumnEditorOptions) => {
//         return (
//             <Dropdown
//                 value={options.value}
//                 options={statuses}
//                 onChange={(e: DropdownChangeEvent) => options.editorCallback!(e.value)}
//                 placeholder="Select a Status"
//                 itemTemplate={(option) => {
//                     return <Tag value={option} severity={getSeverity(option)}></Tag>;
//                 }}
//             />
//         );
//     };

//     const priceEditor = (options: ColumnEditorOptions) => {
//         return <InputNumber value={options.value} onValueChange={(e: InputNumberValueChangeEvent) => options.editorCallback!(e.value)} mode="currency" currency="USD" locale="en-US" />;
//     };

//     const statusBodyTemplate = (rowData: Product) => {
//         return <Tag value={rowData.inventoryStatus} severity={getSeverity(rowData.inventoryStatus)}></Tag>;
//     };

//     const priceBodyTemplate = (rowData: Product) => {
//         return new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(rowData.price);
//     };

//     const allowEdit = (rowData: Product) => {
//         return rowData.name !== 'Blue Band';
//     };

//     return (
//         <div className="card p-fluid">
//             <DataTable value={products} editMode="row" dataKey="id" onRowEditComplete={onRowEditComplete} tableStyle={{ minWidth: '50rem' }}>
//                 <Column field="code" header="Code" editor={(options) => textEditor(options)} style={{ width: '20%' }}></Column>
//                 <Column field="name" header="Name" editor={(options) => textEditor(options)} style={{ width: '20%' }}></Column>
//                 <Column field="inventoryStatus" header="Status" body={statusBodyTemplate} editor={(options) => statusEditor(options)} style={{ width: '20%' }}></Column>
//                 <Column field="price" header="Price" body={priceBodyTemplate} editor={(options) => priceEditor(options)} style={{ width: '20%' }}></Column>
//                 <Column rowEditor={allowEdit} headerStyle={{ width: '10%', minWidth: '8rem' }} bodyStyle={{ textAlign: 'center' }}></Column>
//             </DataTable>
//         </div>
//     );
// }
