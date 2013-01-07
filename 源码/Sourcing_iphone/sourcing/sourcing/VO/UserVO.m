//
//  UserVO.m
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "UserVO.h"
#import "FileUtil.h"
#import "AutoCoding.h"

@implementation UserVO

@synthesize usertype = usertype;
@synthesize useraccount = useraccount;
@synthesize password = password;
@synthesize username = username;
@synthesize userphoto = userphoto;
@synthesize phonenumber = phonenumber;
@synthesize email = email;
@synthesize consultcount = consultcount;
@synthesize auditcount = auditcount;
@synthesize declarecount = declarecount;
@synthesize notifiercount = notifiercount;
@synthesize allconsultcount = allconsultcount;
@synthesize allauditcount = allauditcount;
@synthesize alldeclarecount = alldeclarecount;
@synthesize allnotifiercount = allnotifiercount;

-(void)setUserid:(NSInteger )kuserid
{
    _id = kuserid;
    userid = kuserid;
}
-(NSInteger )Userid
{
    return userid;
}

+(NSString *)getUserInfoFilePath
{
    return [FileUtil createFilePathForLocation:FileDirectoryDocuments withFileName:@"datafile_u" withExtension:@"dat"];
}

+(BOOL)saveLoginUserInfo:(UserVO *)kuservo
{
    NSString *filepath = [UserVO getUserInfoFilePath];
    BOOL result = NO;
     if([[NSFileManager defaultManager] fileExistsAtPath:filepath])
     {
        result = [[NSFileManager defaultManager] removeItemAtPath:filepath error:nil];
     }
    if(kuservo != nil)
    {
        return [NSKeyedArchiver archiveRootObject:kuservo toFile:filepath];
    }
    return result;
}

+(UserVO *)getLastLoginUserInfo
{
    NSString *filepath = [UserVO getUserInfoFilePath];
    if(![[NSFileManager defaultManager] fileExistsAtPath:filepath])
        return nil;
    UserVO * result = [NSKeyedUnarchiver unarchiveObjectWithFile:filepath];
    return result;
}

@end
