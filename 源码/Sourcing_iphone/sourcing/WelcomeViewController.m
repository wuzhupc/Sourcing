//
//  WelcomeViewController.m
//  sourcing
//
//  Created by wuzhu on 12-12-25.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "WelcomeViewController.h"
#import "FileUtil.h"
#import "JsonParser.h"
#import "ChannelVO.h"
#import "ApplicationSet.h"
#import "UserVO.h"

@interface WelcomeViewController ()

@end

@implementation WelcomeViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    [self showNowVerInfo];
    // Do any additional setup after loading the view from its nib.
    
//    //TEST
//    NSString *channelinfo =  [FileUtil getAssetsFileContent:@"channelinfo" oftype:@"json"];
//    //NSLog(@"channelinfo.json:%@" ,channelinfo);
//    ResponseVO *resp = [[ResponseVO alloc] init];
//    NSArray *array = [JsonParser parseJsonToList:channelinfo respVO:&resp ref:nil];
//    if(array == nil||[array count]==0)
//        NSLog(@"转换失败");
//    else
//    {
//        ChannelVO *channelvo = [array objectAtIndex:1];
//        
//        NSLog(@"%d 第2个对象：id:%@ channelid:%@ channelname:%@",[array count],[channelvo Id],[channelvo ChannelID],[channelvo channelName]);
//    }
    //TODO 
}

-(void)viewDidAppear:(BOOL)animated
{
    [super viewDidAppear:animated];
    //启动加载栏目信息
    [self loadChannelInfo];
    //TODO 启动判断用户登录
    [self checkUserLogin];
    //显示两秒后切换到主界面
    [self performSelector:@selector(showHome) withObject:nil afterDelay:2];
}

-(void)checkUserLogin
{
    //DEBUG
//    UserVO *u = [[UserVO alloc] init];
//    [u setUseraccount:@"1"];
//    [u setUserid:@"1"];
//    [u setUsername:@"test"];
//    [u setPassword:@"tset"];
//    [UserVO saveLoginUserInfo:u];
    //
    UserVO *userinfo  = [UserVO getLastLoginUserInfo];
    if(userinfo==nil)
        return;
    //TODO 验证登录用户信息
}

-(void)loadChannelInfo
{
    if ([ApplicationSet shareData].channels == nil)
    {
        NSString *channelinfo =  [FileUtil getAssetsFileContent:@"channelinfo" oftype:@"json"];
        ResponseVO *resp = [[ResponseVO alloc] init];
        NSArray *array = [JsonParser parseJsonToList:channelinfo respVO:&resp ref:nil];
        [ApplicationSet shareData].channels = array;
    }
}

-(void)showHome
{
    //TODO
}

/**
 * 显示当前版本信息
 */
- (void)showNowVerInfo
{
    NSString *verinfo = [[[NSBundle mainBundle] infoDictionary] objectForKey:@"CFBundleVersion"];
    //显示内部版本用@“CFBundleVersion”  内部版本号
    //显示版本用@“CFBndleShortVersionString” 发布版本号
    NSString *verString = NSLocalizedString(@"Ver:%@", @"版本提示信息");
    self.laVersion.text = [NSString stringWithFormat:verString,verinfo];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)viewDidUnload {
    [self setLaVersion:nil];
    [super viewDidUnload];
}
@end
