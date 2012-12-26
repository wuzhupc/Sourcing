//
//  FileUtil.m
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "FileUtil.h"
#import "StringUtil.h"

@implementation FileUtil

+(NSString *)getAssetsFileContent:(NSString *)filename oftype:(NSString *)type
{
    if ([StringUtil isEmptyStr:filename]) {
        return nil;
    }
    //NSString *asset = [NSString stringWithFormat:@"%@\\%@",@"assets",filename];
    NSString *txtPath = [[NSBundle mainBundle] pathForResource:filename  ofType:type inDirectory:@"assets"];
    NSString *string = [[NSString  alloc] initWithContentsOfFile:txtPath encoding:NSUTF8StringEncoding error:nil];
    return string;
}
@end
