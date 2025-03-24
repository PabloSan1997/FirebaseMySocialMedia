import { PencilSquareIcon } from '@heroicons/react/24/solid';
import '../styles/userinfo.scss';
import { stringToDate } from '../utils/stringToDate';
import { useAppDispatch } from '../redux/hook';
import { userActions } from '../redux/slices/userSlice';
import { ShowUserPerfil } from './ShowUserPerfil';
import React from 'react';

interface UserinfoOptions extends UserInfo {
    perfil: boolean;
}
export function UserInfo({ username, urlImage, fullname, born, createCount, description, perfil }: UserinfoOptions) {
    const dispatch = useAppDispatch();
    const openEditProfile = () => {
        dispatch(userActions.setProdileForm({ showProfileForm: true }));
    }
    const [showPerfilPicture, setShowPerfilPicture] = React.useState(false);
    const onClose = () => { setShowPerfilPicture(false); }
    return (
        <div className="userinfo">
            {showPerfilPicture && <ShowUserPerfil src={urlImage} alt={username} onClose={onClose} />}
            <div className="area_info_profile">
                {perfil && (<PencilSquareIcon className='edit_button_picture' onClick={openEditProfile} />)}
                <div className="border_image">
                    {urlImage.trim() && <img src={urlImage} alt={username} onClick={() => setShowPerfilPicture(true)} />}
                </div>
            </div>
            <h2 className='fullname'>{fullname}</h2>
            <h3 className='username'>@{username}</h3>
            <p className="description">{description}</p>
            <div className="area_dates">
                <span>Nacimientos: {stringToDate(born, true)}</span>
                <span>Se unió: {stringToDate(createCount, true)}</span>
            </div>
        </div>
    );
}
