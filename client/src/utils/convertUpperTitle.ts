

export function convertUpperTitle(title:string):string{
    const data = title.split('');
    data[0] = data[0].toLocaleUpperCase();
    return data.join('');
}