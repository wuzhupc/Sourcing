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

@interface UserVO ()
{
@private
    NSInteger userid;
}
@end

@implementation UserVO

@synthesize usertype = usertype_;
@synthesize useraccount = useraccount_;
@synthesize password = password_;
@synthesize username = username_;
@synthesize userphoto = userphoto_;
@synthesize phonenumber = phonenumber_;
@synthesize email = email_;
@synthesize consultcount = consultcount_;
@synthesize auditcount = auditcount_;
@synthesize declarecount = declarecount_;
@synthesize notifiercount = notifiercount_;
@synthesize allconsultcount = allconsultcount_;
@synthesize allauditcount = allauditcount_;
@synthesize alldeclarecount = alldeclarecount_;
@synthesize allnotifiercount = allnotifiercount_;

-(void)setUserid:(NSInteger )kuserid
{
    self.Id = kuserid;
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
