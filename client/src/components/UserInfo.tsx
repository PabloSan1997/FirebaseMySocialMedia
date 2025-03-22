import '../styles/userinfo.scss';
import { stringToDate } from '../utils/stringToDate';

export function UserInfo({ username, urlImage, fullname, born, createCount, description }: UserInfo) {
    return (
        <div className="userinfo">
            <div className="border_image">
                <img src={urlImage.trim()?urlImage:'h'} alt={username} />
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
