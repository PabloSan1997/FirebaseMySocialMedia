

export function UserInfo({ username, urlImage }: UserInfo) {
    return (
        <div className="userinfo">
            {urlImage && <img src={urlImage} alt="" />}
            <h2>{username}</h2>
        </div>
    );
}
