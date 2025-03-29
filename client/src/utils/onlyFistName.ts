


export function onlyFistName(name:string):string {
    const data = name.split(' ');
    const fistname = data[0].split('');
    if(fistname.length <= 6){
        return fistname.join('');
    }
    return fistname.splice(0,6).join('');
}
