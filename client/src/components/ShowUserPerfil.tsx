import { XCircleIcon } from "@heroicons/react/24/solid";
import '../styles/showimage.scss';

export function ShowUserPerfil({src, alt, onClose}: { src: string, alt: string, onClose():void }) {
    return (
        <div className="show_user_perfil">
            <XCircleIcon className="boton_cerrar" onClick={onClose}/>
            <img  src={src} alt={alt}/>
        </div>
    );
}
