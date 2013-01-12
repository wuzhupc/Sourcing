//
//  UserVO.h
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "BaseVO.h"

@interface UserVO : BaseVO


@property (nonatomic,strong)NSString *usertype;
@property (nonatomic,strong)NSString *useraccount;
@property (nonatomic,strong)NSString *password;
@property (nonatomic,strong)NSString *username;
@property (nonatomic,strong)NSString *userphoto;
@property (nonatomic,strong)NSString *phonenumber;
@property (nonatomic,strong)NSString *email;
@property (nonatomic,strong)NSString *consultcount;
@property (nonatomic,strong)NSString *auditcount;
@property (nonatomic,strong)NSString *declarecount;
@property (nonatomic,strong)NSString *notifiercount;
@property (nonatomic,strong)NSString *allconsultcount;
@property (nonatomic,strong)NSString *allauditcount;
@property (nonatomic,strong)NSString *alldeclarecount;
@property (nonatomic,strong)NSString *allnotifiercount;


-(void)setUserid:(NSInteger )kuserid;
-(NSInteger )Userid;

+(BOOL)saveLoginUserInfo:(UserVO *)kuservo;
+(UserVO *)getLastLoginUserInfo;

@end
