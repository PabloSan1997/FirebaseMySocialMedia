


export function stringToDate(timesta:string):string{
    const date = new Date(timesta);
    return date.toLocaleString();
}