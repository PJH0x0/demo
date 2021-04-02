package org.pjh.jetpackdemo.model;


public class User {

    private String skey;
    //0: 不支持指纹登录，1: 支持指纹登录
    private String mSupportFingerPrint = "0";

    private String username;
    private String password;
    private String expiresin;
    private String mobile;
    private String email;
    private String imei;

    private String registerdate;

    private String nickname;
    private String gender;
    private String birthday;
    private String address;
    private String avatar;

    private String hometown;
    private String country;
    private String province;
    private String city;
    private String profession;

    private String accessid;
    private String secretkey;
    private String bucketname;
    private String osstype;
    private String osslocal;
    private String sdktype;

    private String question1;
    private String question2;
    private String question3;

    private String answer1;
    private String answer2;
    private String answer3;

    private String field1;
    private String field2;
    private String field3;
    private String field4;
    private String field5;

    public static final String MALE = "1";
    public static final String FEMALE = "0";

    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getSkey() {
        return skey;
    }

    public void setSkey(String skey) {
        this.skey = skey;
    }

    public String isSupportFingerPrint() {
        return mSupportFingerPrint;
    }

    public void setSupportFingerPrint(String supportFingerPrint) {
        mSupportFingerPrint = supportFingerPrint;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getExpiresIn() {
        return expiresin;
    }

    public void setExpiresIn(String expiresin) {
        this.expiresin = expiresin;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImei() {
        return imei;
    }
    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getRegisterDate() {
        return registerdate;
    }

    public void setRegisterDate(String registerdate) {
        this.registerdate = registerdate;
    }

    public String getNickName() {
        return nickname;
    }
    public void setNickName(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getHomeTown() {
        return hometown;
    }

    public void setHomeTown(String hometown) {
        this.hometown = hometown;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getAccessId() {
        return accessid;
    }

    public void setAccessId(String accessid) {
        this.accessid = accessid;
    }

    public String getSecretKey() {
        return secretkey;
    }

    public void setSecretKey(String secretkey) {
        this.secretkey = secretkey;
    }

    public String getBucketName() {
        return bucketname;
    }

    public void setBucketName(String bucketname) {
        this.bucketname = bucketname;
    }

    public String getOssType() {
        return osstype;
    }

    public void setOssType(String osstype) {
        this.osstype = osstype;
    }

    public String getOssLocal() {
        return osslocal;
    }

    public void setOssLocal(String osslocal) {
        this.osslocal = osslocal;
    }

    public String getSdkType() {
        return sdktype;
    }

    public void setSdkType(String sdkType) {
        this.sdktype = sdkType;
    }

    public String getQuestion1() {
        return question1;
    }

    public void setQuestion1(String question1) {
        this.question1 = question1;
    }

    public String getQuestion2() {
        return question2;
    }

    public void setQuestion2(String question2) {
        this.question2 = question2;
    }

    public String getQuestion3() {
        return question3;
    }

    public void setQuestion3(String question3) {
        this.question3 = question3;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }
    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }
    public String getField4() {
        return field4;
    }

    public void setField4(String field4) {
        this.field4 = field4;
    }

    public String getField5() {
        return field5;
    }

    public void setField5(String field5) {
        this.field5 = field5;
    }

    @Override
    public String toString() {
        //Utils.DBG = true;
        /*if (!Utils.DBG) {
            return super.toString();
        }*/
        StringBuffer buffer = new StringBuffer();
        buffer.append("skey: ").append(skey).append("\n");
        buffer.append("username: ").append(username).append("\n");
        buffer.append("password: ").append(password).append("\n");
        buffer.append("expiresin: ").append(expiresin).append("\n");
        buffer.append("mobile: ").append(mobile).append("\n");
        buffer.append("email: ").append(email).append("\n");
        buffer.append("imei: ").append(imei).append("\n");
        buffer.append("registerdate: ").append(registerdate).append("\n");
        buffer.append("nickname: ").append(nickname).append("\n");
        buffer.append("gender: ").append(gender).append("\n");
        buffer.append("birthday: ").append(birthday).append("\n");
        buffer.append("address: ").append(address).append("\n");
        buffer.append("avatar: ").append(avatar).append("\n");
        buffer.append("hometown: ").append(hometown).append("\n");
        buffer.append("country: ").append(country).append("\n");
        buffer.append("province: ").append(province).append("\n");
        buffer.append("city: ").append(city).append("\n");
        buffer.append("profession: ").append(profession).append("\n");
        buffer.append("accessid: ").append(accessid).append("\n");
        buffer.append("secretkey: ").append(secretkey).append("\n");
        buffer.append("bucketname: ").append(bucketname).append("\n");
        buffer.append("osstype: ").append(osstype).append("\n");
        buffer.append("osslocal: ").append(osslocal).append("\n");
        buffer.append("sdktype: ").append(sdktype).append("\n");
        buffer.append("question1: ").append(question1).append("\n");
        buffer.append("question2: ").append(question2).append("\n");
        buffer.append("question3: ").append(question3).append("\n");
        buffer.append("answer1: ").append(answer1).append("\n");
        buffer.append("answer2: ").append(answer2).append("\n");
        buffer.append("answer3: ").append(answer3).append("\n");
        buffer.append("field1: ").append(field1).append("\n");
        buffer.append("field2: ").append(field2).append("\n");
        buffer.append("field3: ").append(field3).append("\n");
        buffer.append("field4: ").append(field4).append("\n");
        buffer.append("field5: ").append(field5).append("\n");

        return buffer.toString();
    }

}

