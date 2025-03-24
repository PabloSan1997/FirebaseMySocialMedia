


import { EyeIcon, EyeSlashIcon } from "@heroicons/react/24/solid";

export  function ShowPasswordBottom({onClick, state, className}:{onClick():void, state:boolean, className:string}) {

    if(state)
        return <EyeIcon onClick={onClick} className={className}/>
    return <EyeSlashIcon onClick={onClick} className={className}/>
}
