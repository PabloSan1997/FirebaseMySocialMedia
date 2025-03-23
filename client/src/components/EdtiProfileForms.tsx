
import { useAppSelector } from '../redux/hook';
import '../styles/formsuserinfo.scss';

export function EdtiProfileForms() {
    const userstate = useAppSelector(state => state.user);
    if (userstate.showProfileForm)
        return (
            <div className="area_forms_userinfo">EdtiProfileForms</div>
        );
    return null;
}
