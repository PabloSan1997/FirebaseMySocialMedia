

export const loginStorage = {
    read():string{
        if(!localStorage.sixd){
            localStorage.sixd = '';
        }
        return localStorage.sixd
    },
    save(token:string):void{
        localStorage.sixd=token;
    }
}