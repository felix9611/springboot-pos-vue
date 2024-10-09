export const excelHeader = [
    'Product Code',
    'Product Name',
    'Item Code',
    'Brand Name',
    'Type Code ',
    'Type Name',
    'Unit',
    'Description',
    'Retail Price',
    'Department Code',
    'Department Name',
    'Vendor Code',
    'Vendor Name',
    'Other Vendor Name',
    'Vendor Type',
    'Vendor Email',
    'Vendor Phone',
    'Vendor Fax',
    'Vendor Address',
    'Vendor Contact Person',
    'Vendor Remark',
    'Tax',
    'Tax Code',
    'Tax Rate(%)',
    'After Tax',
    'Tax Amount',
    'Location Code 1',
    'Location Name 1',
    'Location Qty 1',
    'Location Code 2',
    'Location Name 2',
    'Location Qty 2'
]

export function formatData(arr: any) {
    return arr.map((obj: any) => {
        const productLocations: { placeCode: string; placeName: string; qty: number }[] = []
    
        // Loop through the keys in the object
        for (const key in obj) {
          // Look for keys that start with 'Special Date Name'
          if (key.startsWith("Location Code")) {
            // Extract the index number from the key
            const index = key.replace("Location Code ", "")
    
            // Find the corresponding 'Special Date' and 'Special Date Remark' (if available)
            const nameKey = `Location Name ${index}`;
            const qtyKey = `Location Qty ${index}`

            
            let timestamp = new Date(obj[nameKey])
            console.log(timestamp, 'test')
            // Construct the memberSpecialDays entry with optional remark
            const memberSpecialDay: { placeCode: string; placeName: string; qty: number } = {
                placeCode: obj[key],
                placeName: obj[nameKey],
              qty: Number(obj[qtyKey])
            };
            
    
            // Add remark if it exists
    
            // Push the entry into the array
            productLocations.push(memberSpecialDay)
          }
        }
    
        // Mapping field names to camelCase
        const transformedObj = {
            productCode: obj["Product Code"],
            productName: obj["Product Name"],
            itemCode: obj["Item Code"],
            brandName: obj["Brand Name"],
            typeCode: obj["Type Code"],
            typeName: obj["Type Name"],
            unit: obj["Unit"],
            description: obj["description"],
            retailPrice: Number(obj["Retail Price"]),
            departmentCode: obj["Department Code"],
            departmentName: obj["Department Name"],
            vendorCode: obj["Vendor Code"],
            vendorName: obj["Vendor Name"],
            vendorOtherName: obj['Other Vendor Name'],
            vendorType: obj['Vendor Type'],
            vendorEmail: obj['Vendor Email'],
            vendorPhone: obj['Vendor Phone'],
            vendorFax: obj['Vendor Fax'],
            vendorAddress: obj['Vendor Address'],
            vendorContactPerson: obj['Vendor Contact Person'],
            vendorRemark: obj['Vendor Remark'],
            tax: obj['Tax'] === 'Yes'? 1 : 0,
            taxCode: obj['Tax Code'],
            taxRate: Number(obj['Tax Rate(%)']) / 100,
            afterTax: Number(obj['After Tax']),
            taxAmount: Number(obj['Tax Amount']),
            productLocations
        };
    
        return transformedObj
      })
}